package dbx.zzzz.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
// temporary disabled
//@Configuration
//@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler( websockethandler(), "/websocket")
		.addInterceptors(new HttpSessionHandshakeInterceptor())
		.setAllowedOrigins("http://localhost:3000");
		
		registry.addHandler(apisockethandler(), "/wsapi")
		.addInterceptors(new HttpSessionHandshakeInterceptor())
		.setAllowedOrigins("http://localhost:3000");
	}
	
	@Bean
	public WebSocketHandler websockethandler() {
		return new SocketTextHandler();
	}

	@Bean
	public WebSocketHandler apisockethandler() {
		return new SocketApiTextHandler();
	}	
	
}
