package com.burak.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;


import java.time.Duration;
import java.time.LocalDateTime;


@SpringBootApplication
public class GatewayserverApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayserverApplication.class, args);
  }

  @Bean
  public RouteLocator gatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
      .route(p -> p.path("/burakerdogan/accounts/**")
        .filters(f -> f.rewritePath("/burakerdogan/accounts/(?<segment>.*)", "/${segment}")
          .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
          .circuitBreaker(config -> config.setName("accountsCircuitBreaker").setFallbackUri("forward:/contactSupport")))
        .uri("http://localhost:8088"))  // Direct URL instead of Eureka

      .route(p -> p.path("/burakerdogan/transactions/**")
        .filters(f -> f.rewritePath("/burakerdogan/transactions/(?<segment>.*)", "/${segment}")
          .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
        .uri("http://localhost:8089"))  // Direct URL instead of Eureka
      .build();
  }

  @Bean
  public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
      .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
      .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
  }
}

