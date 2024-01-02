package com.maa.poker.voting.client;

import jakarta.websocket.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

@ClientEndpoint
public class WebSocketClient {
    private CountDownLatch latch;

    public WebSocketClient(CountDownLatch latch) {
        this.latch = latch;
    }

    @OnOpen
    public void onOpen (Session session) {
        System.out.println("[CLIENT]: Connection established..... \n[CLIENT]: Session ID: " + session.getId() );
        try {
            session.getBasicRemote().sendText("Server is ready.....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage (String message, Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[SERVER RESPONSE]: " + message);
        String clientInput = scanner.nextLine();
        return clientInput;
    }

    @OnClose
    public void onClose (Session session, CloseReason closeReason) {
        System.out.println("[CLIENT]: Session " + session.getId() + " close, because " + closeReason);
        latch.countDown();
    }

    @OnError
    public void onError (Session session, Throwable err) {
        System.out.println("[CLIENT]: Error!!!!!, Session ID: " + session.getId() + ", " + err.getMessage());
    }
}
