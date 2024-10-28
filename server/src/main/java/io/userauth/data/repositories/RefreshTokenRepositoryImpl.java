package io.userauth.data.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import io.userauth.models.RefreshToken;
import jakarta.persistence.EntityManager;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository{

    private final EntityManager entityManager;

    public RefreshTokenRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(RefreshToken refreshToken) {
        entityManager.persist(refreshToken);
    }

    @Override
    public RefreshToken findTokenById(UUID id) {
        return entityManager.find(RefreshToken.class, id);
    }

    
}
