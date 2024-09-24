package io.userauth.models.entities;

import org.hibernate.validator.constraints.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


// @Entity
// @Table(name = "registrationSession", 
//         uniqueConstraints = {
//             @UniqueConstraint(columnNames = {"user_id"}),
//         }
// )
// public class registerSessionEntity {
    
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)
//     @Column(name = "session_token")
//     private UUID sessionToken;
    
//     @Column(name = "email")
//     private String email;
    

//     public UUID getSessionToken() {
//         return this.sessionToken;
//     }

//     public void setSessionToken(UUID sessionToken) {
//         this.sessionToken = sessionToken;
//     }

//     public String getEmail() {
//         return this.email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }


// }
