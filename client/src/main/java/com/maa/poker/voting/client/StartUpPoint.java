package com.maa.poker.voting.client;

import jakarta.websocket.DeploymentException;
import org.glassfish.tyrus.client.ClientManager;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

public class StartUpPoint {
    private static CountDownLatch latch;

    public static void main(String args[]){
        System.out.println("Hello world Client!");
        latch = new CountDownLatch(1);
        ClientManager clientManager = ClientManager.createClient();
        URI uri = null;
        try {
            uri = new URI("ws://localhost:8080/java/demoApp");
            clientManager.connectToServer(new WebSocketClient(latch), uri);
            latch.await();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
