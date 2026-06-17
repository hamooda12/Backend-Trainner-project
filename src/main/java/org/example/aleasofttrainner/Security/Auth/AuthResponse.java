package org.example.aleasofttrainner.Security.Auth;

import lombok.Builder;
import lombok.Data;
import org.example.aleasofttrainner.Security.Role;

@Data
@Builder
public class AuthResponse {

    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private String email;
    private Role role;
}
