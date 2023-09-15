package ru.itmo.se.bl.lab3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.se.bl.lab3.entity.User;
import ru.itmo.se.bl.lab3.entity.UserRole;
import ru.itmo.se.bl.lab3.exception.UserAlreadyRegisteredException;
import ru.itmo.se.bl.lab3.exception.UserNotFoundException;
import ru.itmo.se.bl.lab3.repository.BookingRepository;
import ru.itmo.se.bl.lab3.repository.UserRepository;
import ru.itmo.se.bl.lab3.security.PassportEncoder;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PassportEncoder passportEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public User getById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getByLogin(String login) throws UserNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
    }

    public User getByLoginAndPassword(String login, String password) throws UserNotFoundException {
        Objects.requireNonNull(login, "Name is mandatory");
        Objects.requireNonNull(password, "Password is mandatory");

        return userRepository.findBy(u ->
                        Objects.equals(login, u.getLogin()) &&
                        passwordEncoder.matches(password, u.getPasswordHash()))
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    public User getByLoginPassport(String login, String passport) throws UserNotFoundException  {
        Objects.requireNonNull(login, "Login is mandatory");
        Objects.requireNonNull(passport, "Passport is mandatory");

        return userRepository.findBy(u ->
                        Objects.equals(login, u.getLogin()) &&
                                passportEncoder.matches(passport, u.getPassportHash()))
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    public void registerUser(User user) throws UserAlreadyRegisteredException {
        Objects.requireNonNull(user);

        if (userRepository.findByLogin(user.getLogin()).isPresent() || userRepository.save(user) < 0)
            throw new UserAlreadyRegisteredException(user.getLogin());
    }

    public boolean promoteUser(String login) throws UserNotFoundException {
        Objects.requireNonNull(login);

        User user = getByLogin(login);
        user.setRole(UserRole.ADMIN);
        return userRepository.save(user) >= 0;
    }

    public void removeUser(Integer id) throws UserNotFoundException  {
        User user = new User();
        user.setId(id);

        if (!userRepository.remove(user))
            throw new UserNotFoundException(id);
    }

//    public void addBookingForUser(Integer userId, Booking booking) throws UserNotFoundException {
//        Integer bookingId = booking.getId();
//
//        if (bookingRepository.findById(bookingId).isEmpty())
//            bookingRepository.save(booking);
//
//        getById(userId).addBooking(bookingId);
//    }
}
