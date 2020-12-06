package dbx.zzzz.services;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

@Component
public class SocketApiTextHandler extends TextWebSocketHandler{
	private Gson gson;
	private WebSocketSession session;	
	public SocketApiTextHandler () {
		gson = new Gson();
	}
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
	  Msg m = null;
	  String payload = message.getPayload();

	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	   Msg m= new Msg ();
	   m.setIdx (10L);
	   m.setMsg ("ws api message");
	   TextMessage msg = new TextMessage (gson.toJson(m));
	   session.sendMessage (msg);
	   this.session =session;
	}
	
	
	public void sendMessage (String msg) throws IOException {
	   if (session != null && session.isOpen()) {
		   TextMessage tmsg = new TextMessage (msg);
		   session.sendMessage (tmsg);
	   }
	}
	
}
