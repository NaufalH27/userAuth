package io.userauth.unit.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import io.userauth.data.entities.UserEntity;
import io.userauth.data.repositories.UserRepository;
import io.userauth.util.PasswordUtils;

@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private String mockHashedPassword;

    @BeforeEach
    public void setUp() {
        mockHashedPassword = PasswordUtils.hashPassword("mockPasswordFromMomoi");
    }


    //Use Case 1: successful create user
    @Test
    public void testSaveUser_ValidData() {
        UserEntity correctEntity = new UserEntity();
        correctEntity.setName("momoiBalap");
        correctEntity.setEmail("momoi@gmail.com");
        correctEntity.setPasswordHash(mockHashedPassword);

        repository.save(correctEntity);

        assertNotNull(repository.findByName(correctEntity.getName()));
        assertNotNull(repository.findByEmail(correctEntity.getEmail()));

    }

    // Use Case 2: Name Field Empty
    @Test
    public void testSaveUser_EmptyName() {
        UserEntity entityEmptyName = new UserEntity();
        entityEmptyName.setName(null);
        entityEmptyName.setEmail("momoi@gmail.com");
        entityEmptyName.setPasswordHash(mockHashedPassword);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entityEmptyName);
        });

    }

    // Use Case 3: Email Field Empty
    @Test
    public void testSaveUser_EmptyEmail() {
        UserEntity entityEmptyEmail = new UserEntity();
        entityEmptyEmail.setName("momoiBalap2");
        entityEmptyEmail.setEmail(null);
        entityEmptyEmail.setPasswordHash(PasswordUtils.hashPassword("racing"));

        repository.save(entityEmptyEmail);

        assertNotNull(repository.findByName(entityEmptyEmail.getName()));
        assertNull(repository.findByEmail(entityEmptyEmail.getEmail()));
    }

    // Use Case 4: Password Field Empty
    @Test
    public void testSaveUser_EmptyPassword() {
        UserEntity entityAssignedNullPassword = new UserEntity();
        entityAssignedNullPassword.setName("momoiBalap3");
        entityAssignedNullPassword.setEmail("momoi2@gmail.com");
        entityAssignedNullPassword.setPasswordHash(null);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(entityAssignedNullPassword);
        });
    }

    //findById nonexistanceEntity
    @Test
    public void testFindById_nonexistenceEntity() {
        assertNull(repository.findById(0));
    }

    @Test
    public void testFindByName_nonexistenceEntity() {
        assertNull(repository.findByName("nonExcistenceWIthIllegalSymbol$% #@"));
    }

    @Test
    public void testFindByEmail_nonexistenceEntity() {
        assertNull(repository.findByEmail("invalid-email-format@nonexistent-domain"));
    }

    
}
