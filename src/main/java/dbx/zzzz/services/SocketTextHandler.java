package dbx.zzzz.services;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SocketTextHandler  extends TextWebSocketHandler{
	private Gson gson;
	private WebSocketSession session;
	public SocketTextHandler () {
		gson = new Gson();
	}
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
	  Msg m = null;
	  String payload = message.getPayload();
	  m = gson.fromJson(payload, Msg.class);
	  if (m != null) {
		  session.sendMessage (new TextMessage ("idx=" + m.getIdx() + ", msg=" + m.getMsg()));
	  } else {
		  session.sendMessage(new TextMessage("Hi how may we help you?"));
	  }
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	   Msg m= new Msg ();
	   m.setIdx (10L);
	   m.setMsg ("ws message");
	   TextMessage msg = new TextMessage (gson.toJson(m));
	   session.sendMessage (msg);
	   this.session =session;
	}
	
	public boolean isOpen () {
		if (session == null) return false;
		return session.isOpen();
	}
	
	public InetSocketAddress getRemoteAddress() {
		if (session == null) return null;
		return session.getRemoteAddress();
	}
	
	public void sendMessage (String msg) throws IOException {
	   if (session != null && session.isOpen()) {
		   TextMessage tmsg = new TextMessage (msg);
		   this.session.sendMessage (tmsg);
	   }
	}
	
}
