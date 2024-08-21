package com.example.bookstoreapi.health;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom health check logic
        boolean isHealthy = checkHealth();
        if (isHealthy) {
            return Health.up().withDetail("Custom Health Check", "All systems go!").build();
        } else {
            return Health.down().withDetail("Custom Health Check", "Something went wrong!").build();
        }
    }

    private boolean checkHealth() {
        // Implement your custom health check logic here
        return true;
    }
}
