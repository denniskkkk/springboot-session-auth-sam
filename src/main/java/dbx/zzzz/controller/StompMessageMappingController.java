package dbx.zzzz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StompMessageMappingController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;   
	private SimpleDateFormat dateFormat = new  SimpleDateFormat("HH:mm:ss");

	@MessageMapping("/userall")   // become path = /app/userall
	@SendTo("/topic/user")  // topic path that receive message
	public String stompTopics(String message) {
		String date = dateFormat.format(new Date());
		return( "stomp return message: " + message + ": " + date + ": ");
	}
	
	@MessageMapping("/userenq")   // become path = /app/userall
	@SendTo("/queue/user")  // topic path that receive message
	public String stompQueues(String message) {
		String date = dateFormat.format(new Date());
		return( "stomp return message: " + message + ": " + date + ": ");
	}
	
	@MessageExceptionHandler
	public void handleExceptions(Throwable t) {
	  log.error("Error handling message: " + t.getMessage());
	}
	
	
	public void sendMessage(String message){
       simpMessagingTemplate.convertAndSend("/topic/user",
                "stomp message " + " at " + new Date().toString());		
	}	
	

}
