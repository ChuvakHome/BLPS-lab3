package ru.itmo.se.bl.lab3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab3.data.UsersXmlCollection;
import ru.itmo.se.bl.lab3.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class XmlUserRepositoryImpl implements UserRepository {
    private final UsersXmlCollection usersCollection;

    @Autowired
    public XmlUserRepositoryImpl(UsersXmlCollection collection) {
        usersCollection = collection;
    }

    public List<User> findAll() {
        return usersCollection.getUserList();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(usersCollection.getById(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(usersCollection.get(u -> Objects.equals(login, u.getLogin())));
    }

    @Override
    public Optional<User> findBy(Predicate<User> predicate) {
        return Optional.ofNullable(usersCollection.get(predicate));
    }

    @Override
    public int count() {
        return usersCollection.count();
    }

    @Override
    public Integer save(User u) {
        if (u != null) {
            if (u.getId() != null) {
                User tmp = usersCollection.get(user -> user.getId().equals(u.getId()));
                usersCollection.removeUser(tmp);
            }

            return usersCollection.addUser(u);
        }
        else
            return -1;
    }

    @Override
    public boolean remove(User u) {
        return usersCollection.removeUser(u);
    }
}
