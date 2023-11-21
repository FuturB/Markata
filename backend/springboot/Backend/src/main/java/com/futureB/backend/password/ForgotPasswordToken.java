package com.futureB.backend.password;

import com.futureB.backend.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private boolean isUsed;
    @ManyToOne
    private User user;

    public ForgotPasswordToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.dateTime = LocalDateTime.now().plusMinutes(10);
        this.user = user;
        this.setUsed(false);
    }
}
