package ru.itmo.se.bl.lab3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String login;
    private String passportHash;
    private String passwordHash;
    private UserRole role;
    private List<Integer> booksIdsList;

    public boolean equals(Object o) {
        if (id == null)
            return false;
        else
            return o instanceof User u && u.getId() != null && u.getId().equals(id);
    }

    public int hashCode() {
        return id;
    }

    public void addBooking(Integer bookingId) {
        booksIdsList.add(bookingId);
    }
}
