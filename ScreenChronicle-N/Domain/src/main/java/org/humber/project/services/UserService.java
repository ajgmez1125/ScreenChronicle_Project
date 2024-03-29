package org.humber.project.services;

import org.humber.project.domain.User;

public interface UserService
{
    User getUser(String user_id);
    void addUser(User user);
    void updateUser(User user);    
}
