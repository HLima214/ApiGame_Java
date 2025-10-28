package com.curso.gameapi;

import com.curso.gameapi.security.JwtService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class JwtServiceTest {

    @Test
    void generate_and_validate_token() {
        JwtService svc = new JwtService("change-me-in-prod-32-chars-minimum-secret-key-123456", 3600000);
        String token = svc.generateToken("user", Map.of("role","ROLE_USER"));
        assertNotNull(token);
        assertEquals("user", svc.extractUsername(token));
        assertTrue(svc.isTokenValid(token, "user"));
        assertFalse(svc.isTokenValid(token, "other"));
    }
}