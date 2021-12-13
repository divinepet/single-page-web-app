package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(PasswordEncoder passwordEncoder, UsersRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean signIn(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword());
    }

    public User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.get();
    }

    public boolean signUp(String email, String firstName, String lastName, String phone, String password) throws SQLException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return false;
        }
        userRepository.save(new User(email, firstName, lastName, phone, passwordEncoder.encode(password), null, null));
        return true;
    }

    public String getPicName(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.get().getPicName();
    }

    public void setPicName(String email, String picName) {
        userRepository.setUserPic(email, picName);
    }

    public void updateLogInfo(String email, String ip) {
        userRepository.setLogInfo(email,
                "'" + new SimpleDateFormat("LLLL d. yyyy/H:mm")
                        .format(new Date()) + "/" + ip + "'");
    }

    public List<String> getLogInfo(String email) {
        return (userRepository.getLogInfo(email));
    }
}