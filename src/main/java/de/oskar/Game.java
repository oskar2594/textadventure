package de.oskar;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import de.oskar.commands.Parser;
import de.oskar.utils.Console;
import de.oskar.utils.Reader;
import de.oskar.world.World;
import de.oskar.world.WorldBuilder;
import de.oskar.world.player.Inventory;
import de.oskar.world.player.Player;

public class Game {
    private static String ROOT = ".\\resources\\";
    public static Console console;
    public static Parser parser;
    public static World world;
    public static Player player;

    public static final Scanner scanner = new Scanner(System.in);;
    private static Input input;

    /**
     * Initializes the game
     */
    public static void init() {
        File configFile = new File(ROOT + "config.yaml");
        createConsole(configFile);

        File itemsFile = new File(ROOT + "items.yaml");
        createRawItems(itemsFile);

        File roomsFile = new File(ROOT + "rooms.yaml");
        createWorld(roomsFile);

        parser = new Parser();

        player = new Player();
        player.setPosition(world.getLocation("start_room"));
        input = new Input(parser);
        start();
    }

    private static void createRawItems(File itemsFile) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            JsonNode rootNode = mapper.readTree(itemsFile);
            JsonNode itemsNode = rootNode.get("items");
            Inventory.allItems = WorldBuilder.buildItems(itemsNode);
        } catch (IOException e) {
            System.out.println("Error while reading items config!");
            e.printStackTrace();
        }
    }

    private static void createWorld(File roomsFile) {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            JsonNode rootNode = mapper.readTree(roomsFile);
            JsonNode roomsNode = rootNode.get("rooms");
            world = WorldBuilder.buildWorld(roomsNode);
        } catch (IOException e) {
            System.out.println("Error while reading rooms config!");
            e.printStackTrace();
        }
    }

    private static void createConsole(File configFile) {
        try {
            console = Reader.readYAML(configFile, Console.class, "console");
        } catch (IOException e) {
            System.out.println("Error while reading console config!");
            e.printStackTrace();
        }
    }

    public static void start() {
        player.getPosition().visit();
        input.start();
    }

    public static void endGame() {
        input.stop();
        scanner.close();
        System.out.println("Danke fürs Spielen! :)");
    }
}
