package com.maa.poker.voting.server;

import jakarta.websocket.DeploymentException;
import org.glassfish.tyrus.server.Server;

import java.util.Scanner;

public class StartUpPoint {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        try {
            System.out.println("[SERVER]: Please provide the host on witch the server should start");
            String host = inputScanner.next();
            System.out.println("[SERVER]: Please provide the port on witch the server should start");
            int port = inputScanner.nextInt();
            Server server = new Server(host, port, "/java", null, WebSocketServerEndpoint.class);
            runServer(host, port, inputScanner);
        } finally {
            inputScanner.close();
        }
    }

    private static void runServer(String host, int port, Scanner inputScanner) {
        try {
            Server server = new Server(host, port, "/java", null, WebSocketServerEndpoint.class);
            server.start();
            System.out.println("[SERVER]: Server is up and running.....");
            boolean running = true;
            System.out.println("[SERVER]: Press 't' to terminate server.....");
            System.out.println(String.format("[SERVER]: Client connection credential:\n[%s][%d]", host, port));
            inputScanner.nextLine();
            while (running) {
                running = processInputLine(server, inputScanner);
            }
        } catch (DeploymentException e) {
            e.printStackTrace();
        }

    }

    private static boolean processInputLine(Server server, Scanner inputScanner) {
        String inputline = inputScanner.nextLine();
        if ("t".equalsIgnoreCase(inputline.trim())) {
            System.out.println("[SERVER]: Server successfully terminated.....");
            server.stop();
            return false;
        }
        System.out.println("[SERVER]: Invalid input!!!!!");
        return true;

    }
}
