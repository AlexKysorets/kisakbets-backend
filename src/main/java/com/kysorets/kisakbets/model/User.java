package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;
    private boolean isVerified;
    @DocumentReference
    private Collection<Role> roles = new ArrayList<>();
    private String code;
    private String passwordCode;

    public User(String username, String password, String email, boolean isVerified, Collection<Role> roles, String code, String passwordCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isVerified = isVerified;
        this.roles = roles;
        this.code = code;
        this.passwordCode = passwordCode;
    }
}
