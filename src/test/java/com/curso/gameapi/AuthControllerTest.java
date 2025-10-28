package com.curso.gameapi;

import com.curso.gameapi.controller.AuthController;
import com.curso.gameapi.security.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Test
    void login_returns_token_using_mocks() {
        AuthenticationManager manager = mock(AuthenticationManager.class);
        JwtService jwt = Mockito.spy(
                new JwtService("change-me-in-prod-32-chars-minimum-secret-key-123456", 3600000)
        );

        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("admin");

        var authorities = java.util.List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        when(auth.getAuthorities()).thenAnswer(inv -> authorities);

        when(manager.authenticate(any())).thenReturn(auth);
        doReturn("fake-token").when(jwt).generateToken(anyString(), Mockito.<String, Object>anyMap());

        AuthController controller = new AuthController(manager, jwt);
        var response = controller.login(new AuthController.LoginRequest("admin", "admin"));

        assertNotNull(response.getBody());
        assertEquals("fake-token", response.getBody().token());

        verify(manager).authenticate(any());
        verify(jwt).generateToken(eq("admin"), Mockito.<String, Object>anyMap());
    }
}