package io.userauth.service;

import java.util.UUID;

public interface UserManagementService {
    public void banUser(UUID userId);
    public void deleteUserFromUser(UUID userId);
    public void deleteUserFromAdmin(UUID userId);
}
