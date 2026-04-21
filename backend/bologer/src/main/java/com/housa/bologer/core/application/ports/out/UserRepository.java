package com.housa.bologer.core.application.ports.out;
import com.housa.bologer.core.domain.entities.user.User;
public interface UserRepository {
    void save(User user);
}
