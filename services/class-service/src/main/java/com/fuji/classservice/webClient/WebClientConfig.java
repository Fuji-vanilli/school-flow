package com.fuji.classservice.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(addJwtToken());
    }

    private ExchangeFilterFunction addJwtToken() {
        return (clientRequest, next)-> {
            ClientRequest newClientRequest= ClientRequest.from(clientRequest)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "+extractToken())
                    .build();

            return next.exchange(newClientRequest);
        };
    }

    private String extractToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String tokenValue= "";

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            tokenValue = jwtAuthenticationToken.getToken().getTokenValue();
        }

        return tokenValue;
    }
}
