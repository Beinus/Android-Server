package com.example.chatserver.model.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Enables a simple message broker for pub/sub messaging.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Used for sending messages to specific connected users.
        registry.enableSimpleBroker("/user"); // Allows sending messages directly to a particular connected user.

        // Used for broadcasting messages to all connected clients.
        registry.setApplicationDestinationPrefixes("/app"); // Base path for these application-wide broadcast messages.

        // Reinforces the prefix used for user destinations.
        registry.setUserDestinationPrefix("/user");
    }

    // Registers a WebSocket endpoint at /ws that supports SockJS fallback for browsers that don't natively support WebSockets.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // ws means websocket
                .withSockJS();
    }

    // JSON serialization/deserialization.
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);

        // Returns false to indicate that Spring shouldn't add default converters (since a custom JSON converter is provided).
        return false;
    }
}
