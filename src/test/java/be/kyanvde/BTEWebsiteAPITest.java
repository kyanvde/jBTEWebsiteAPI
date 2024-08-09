package be.kyanvde;

import be.kyanvde.model.HealthCheck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BTEWebsiteAPITest {

    @Test
    void healthCheckReturnsCorrectly() {
        HealthCheck healthCheck = BTEWebsiteAPI.healthCheck();
        System.out.println(healthCheck);
        assertThat(healthCheck).isNotNull();
    }
}