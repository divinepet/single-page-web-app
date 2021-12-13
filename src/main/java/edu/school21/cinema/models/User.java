package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class User {
    private final String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private final String password;
    private String picName;
    private String[] logInfo;
}
