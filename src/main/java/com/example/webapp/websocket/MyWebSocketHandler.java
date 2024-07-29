package com.example.webapp.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> sessions = new ArrayList<>();
	
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
    
    public void sendMessage(String message) throws Exception {
    	for (WebSocketSession ses : sessions) {
    		if (ses.isOpen()) {
    			ses.sendMessage(new TextMessage(""));
    		}
    	}
    }
}
