package io.userauth.data.session.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registration_session")
public class RegisterSession {
    
    @Id
    @Column(name = "session_token")
    private UUID sessionToken;
    
    private String email;

    @Size(max = 5)
    private int OTP;

    
    
}
