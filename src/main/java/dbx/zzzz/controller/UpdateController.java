package dbx.zzzz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@EnableScheduling
public class UpdateController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;   
	private SimpleDateFormat dateFormat = new  SimpleDateFormat("HH:mm:ss");
	@Scheduled (fixedRate = 2000)
	public void updateMessage () {
		//log.info("stomp update message");
		String date = dateFormat.format(new Date());
        simpMessagingTemplate.convertAndSend("/topic/user", "Stomp update message: " + date);		
	}	
}
