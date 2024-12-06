        package com.pet.cafe.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.messaging.simp.config.MessageBrokerRegistry;
        import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
        import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
        import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

        @Configuration
        @EnableWebSocketMessageBroker
        public class ChatConfig implements WebSocketMessageBrokerConfigurer {
            @Override
            public void registerStompEndpoints(StompEndpointRegistry registry) {
                registry.addEndpoint("/ws")
                        .setAllowedOrigins("*");
            }
            @Override
            public void configureMessageBroker(MessageBrokerRegistry registry) {
                registry.setApplicationDestinationPrefixes("/hotel");
                registry.enableSimpleBroker("/topic", "/queue", "/hotel");
                registry.setUserDestinationPrefix("/user");
            }
        }
