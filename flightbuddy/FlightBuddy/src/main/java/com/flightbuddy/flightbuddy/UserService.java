package com.flightbuddy.flightbuddy;

import java.util.ArrayList;
import java.util.List;

public class UserService
{
    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Defaultowy admin
        users.add(new User(
                "Admin", "Admin",
                "admin@flightbuddy.com", "000000000",
                "admin", User.Role.ADMIN
        ));
    }

    public boolean emailExists(String email) {
        return users.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public boolean register(User user) {
        // sprawdzenie, czy istnieje użytkownik z takim emailem lub telefonem
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail()) ||
                    u.getPhone().equals(user.getPhone())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public User login(String email, String password) {
        return users.stream()
                .filter(u ->
                        u.getEmail().equalsIgnoreCase(email)
                                && u.getPassword().equals(password)
                )
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllUsers() {return users;}
    public void removeUser(User user) {users.remove(user);}
}
