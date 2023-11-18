package com.futureB.backend.password;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String token;
    private String newPassword;
}
