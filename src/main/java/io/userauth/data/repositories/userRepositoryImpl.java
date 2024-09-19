package io.userauth.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.data.entities.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class userRepositoryImpl implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;

    public userRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(UserEntity entity){
        entityManager.persist(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public UserEntity findById(int id){
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByName(String name) {
        try {
            String query = "SELECT u FROM UserEntity u WHERE u.name = :name"; 
            return entityManager.createQuery(query, UserEntity.class)
                                .setParameter("name", name)
                                .getSingleResult();
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity findByEmail(String email){
        try {
            String query =  "SELECT u FROM UserEntity u WHERE u.email = :email";
            return entityManager.createQuery(query, UserEntity.class)
                                .setParameter("email", email)
                                .getSingleResult();
            
        } catch (NoResultException e) {
            return null;
        }
      
    }
    
}
