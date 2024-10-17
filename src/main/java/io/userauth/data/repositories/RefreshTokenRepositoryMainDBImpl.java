package io.userauth.data.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.userauth.models.RefreshToken;
import jakarta.persistence.EntityManager;

@Repository
public class RefreshTokenRepositoryMainDBImpl implements RefreshTokenRepository{

    private final EntityManager entityManager;

    public RefreshTokenRepositoryMainDBImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addToken(RefreshToken refreshToken) {
        entityManager.persist(refreshToken);
    }

    @Override
    public RefreshToken findTokenById(UUID id) {
        return entityManager.find(RefreshToken.class, id);
    }

    
}
