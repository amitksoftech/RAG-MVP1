package com.ai.applications.rag.ragmvp1.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app.bootstrap")
public record BootstrapProperties(
        @NotBlank String adminUsername,
        @NotBlank String adminPassword,
        @NotBlank String userUsername,
        @NotBlank String userPassword
) {
}
