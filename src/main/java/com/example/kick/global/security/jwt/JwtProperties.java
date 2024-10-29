package com.example.kick.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private final String secretKey;
    private final Long accessExp;
    private final String header;
    private final String prefix;

    public JwtProperties(String secretKey, Long accessExp, String header, String prefix) {
        if (secretKey.length() < 32) {
            throw new IllegalArgumentException("secrete key");
        }
        this.secretKey = secretKey;
        this.accessExp = accessExp;
        this.header = header;
        this.prefix = prefix;
    }
}
