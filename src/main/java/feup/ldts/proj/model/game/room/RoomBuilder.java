package feup.ldts.proj.model.game.room;

import feup.ldts.proj.controller.game.elements.strategies.BiteStrategy;
import feup.ldts.proj.controller.game.elements.strategies.ShootStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.Passage;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.Wall;
import feup.ldts.proj.model.game.elements.monsters.Skeleton;
import feup.ldts.proj.model.game.elements.monsters.Zombie;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomBuilder {
    private List<String> lines;
    private final int depth, roomNum;

    public RoomBuilder(int depth, int roomNum) throws FileNotFoundException {
        this.depth = depth;
        this.roomNum = roomNum;

        URI roomPath = constructRoomFileURI(depth, roomNum);
        this.lines = loadLinesFromFiles(new File(roomPath));
    }

    public URI constructRoomFileURI(int depth, int roomNum) {
        try {
            String roomPath = "rooms/depth" + depth + '/' + "room" + roomNum + ".txt";
            URL resource = getClass().getClassLoader().getResource(roomPath);
            return resource.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> loadLinesFromFiles(File file) throws FileNotFoundException {
        List<String> fileContent = new ArrayList<>();
        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine())
            fileContent.add(fileReader.nextLine());

        return fileContent;
    }

    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++)
                if (line.charAt(col) == '#') walls.add(new Wall(new Position(col, row)));
        }
        return walls;
    }

    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (line.charAt(col) == '\"')
                    monsters.add(new Zombie(new Position(col, row), depth, new BiteStrategy()));
                else if (line.charAt(col) == '\'')
                    monsters.add(new Skeleton(new Position(col, row), depth, new ShootStrategy()));
            }
        }
        return monsters;
    }

    public void setPlayerPosition(Player player) {
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++)
                if (line.charAt(col) == 'X') player.setPosition(new Position(col ,row));
        }
    }

    public Passage createPassage() {
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++)
                if (line.charAt(col) == 'O') return new Passage(new Position(col, row));
        }
        return null;
    }

    public Room createRoom(Player player) throws URISyntaxException, FileNotFoundException {
        Room room = new Room(depth);
        room.setWalls(createWalls());
        room.setMonsters(createMonsters());
        setPlayerPosition(player);
        room.setPlayer(player);
        room.setPassage(createPassage());

        return room;
    }
}
