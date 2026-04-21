package com.housa.bologer.infrastructure.persistence.user;

import com.housa.bologer.core.application.ports.out.UserRepository;
import com.housa.bologer.core.domain.entities.user.User;
import java.lang.reflect.Field;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(readField(user, "email"));
        entity.setPassword(readField(user, "password"));
        jpaUserRepository.save(entity);
    }

    private String readField(User user, String fieldName) {
        try {
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(user);
            return value == null ? null : value.toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Could not read User field: " + fieldName, e);
        }
    }
}
