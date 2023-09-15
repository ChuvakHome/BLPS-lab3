package ru.itmo.se.bl.lab3.data;

import jakarta.xml.bind.JAXBException;
import ru.itmo.se.bl.lab3.entity.User;
import ru.itmo.se.bl.lab3.marshaler.XmlUserMarshaler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UsersXmlCollection {
    private final List<User> userList;

    public UsersXmlCollection() {
        try {
            userList = XmlUserMarshaler.readFromXML();
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public Integer addUser(User user) {
        if (userList.contains(user))
            return -1;

        if (user.getId() == null)
            user.setId(userList.size() + 1);

        userList.add(user);

        try {
            XmlUserMarshaler.writeToXML(userList);

            return user.getId();
        } catch (JAXBException e) {
            userList.remove(user);

            return -1;
        }
    }

    public boolean removeUser(User user) {
        if (user != null && userList.contains(user)) {
            userList.remove(user);
            try {
                XmlUserMarshaler.writeToXML(userList);
                return true;
            } catch (JAXBException e) {
                userList.add(user);
                return false;
            }
        }
        else
            return false;
    }

    public User get(Predicate<User> predicate) {
        for (User user: userList) {
            if (predicate.test(user))
                return user;
        }

        return null;
    }

    public List<User> getAll(Predicate<User> predicate) {
        ArrayList<User> list = new ArrayList<>();

        for (User user: userList) {
            if (predicate.test(user))
                list.add(user);
        }

        return list;
    }

    public int count() {
        return userList.size();
    }

    public User getById(Integer id) {
        User user = new User();
        user.setId(id);
        int index = userList.indexOf(user);

        return index >= 0 ? userList.get(index) : null;
    }
}
