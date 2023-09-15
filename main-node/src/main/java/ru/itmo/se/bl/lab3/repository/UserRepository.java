package ru.itmo.se.bl.lab3.repository;

import org.springframework.stereotype.Repository;
import ru.itmo.se.bl.lab3.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public interface UserRepository {
    Optional<User> findById(Integer id);

    List<User> findAll();

    Optional<User> findByLogin(String login);

    Optional<User> findBy(Predicate<User> predicate);

    int count();

    Integer save(User u);

    boolean remove(User u);
}
