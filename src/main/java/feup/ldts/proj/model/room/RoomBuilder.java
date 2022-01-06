package feup.ldts.proj.model.room;

import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Weapon;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.Wall;

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
    private int depth, roomNum;

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

        for (int col = 0; col < lines.size(); col++) {
            String line = lines.get(col);
            for (int row = 0; row < line.length(); row++)
                if (line.charAt(row) == '#') walls.add(new Wall(col, row));
        }
        return walls;
    }

    public List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();

        for (int col = 0; col < lines.size(); col++) {
            String line = lines.get(col);
            for (int row = 0; row < line.length(); row++)
                if (line.charAt(row) == 'M') monsters.add(new Monster(col, row, depth));
        }
        return monsters;
    }

    public Player createPlayer() {
        for (int col = 0; col < lines.size(); col++) {
            String line = lines.get(col);
            for (int row = 0; row < line.length(); row++)
                if (line.charAt(row) == 'X') return new Player(col, row);
        }
        return null;
    }

    public Position createGate() {
        for (int col = 0; col < lines.size(); col++) {
            String line = lines.get(col);
            for (int row = 0; row < line.length(); row++)
                if (line.charAt(row) == 'O') return new Position(col, row);
        }
        return null;
    }

    public Room createRoom(Player player) throws URISyntaxException, FileNotFoundException {
        Room room = new Room(depth);
        room.setWalls(createWalls());
        room.setMonsters(createMonsters());
        room.setPlayer(createPlayer());
        room.setGate(createGate());

        room.getPlayer().setWeapon(player.getWeapon());
        room.getPlayer().setMaxHP(player.getMaxHP());
        room.getPlayer().setHP(player.getHP());

        return room;
    }
}