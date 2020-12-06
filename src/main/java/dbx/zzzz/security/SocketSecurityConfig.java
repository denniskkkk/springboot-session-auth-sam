package dbx.zzzz.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
// disable service
//@Configuration
public class SocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
	@Override 
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) { 
	    messages
	      .simpDestMatchers("/websocket/**").authenticated()
	      .anyMessage().authenticated(); 
	}
    @Override
    protected boolean sameOriginDisabled() {
        // We need to access this directly from apps, so can't do cross-site checks
        return true;
    }	
}
