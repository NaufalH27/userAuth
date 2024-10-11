package io.userauth.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "refresh_token", 
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"username"}),
            @UniqueConstraint(columnNames = {"email"}),
        })
public class RefreshToken {

    @Column(nullable=false)
    UUID token;
    
    @Column(nullable=false)
    UUID userId; 

    Boolean isRevoked = false;

    


}
