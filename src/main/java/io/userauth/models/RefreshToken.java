package io.userauth.models;

import java.util.Date;
import java.util.UUID;

import io.userauth.constant.TokenStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    UUID token;
    
    @Column(nullable=false)
    UUID userId; 

    @Enumerated(EnumType.STRING)
    TokenStatus status = TokenStatus.ACTIVE;

    @Column(nullable=false)
    Date issuedAt;

    @Column(nullable=false)
    Date expiredAt;
    
}
