package com.flightbuddy.flightbuddy;

import java.util.ArrayList;
import java.util.List;

public class UserService
{
    private final List<User> users = new ArrayList<>();

    // Defaultowy administrator
    public UserService() {
        users.add(new User(
                "ADMIN", "ADMIN",
                "admin@flightbuddy.com", "000000000",
                "admin", User.Role.ADMIN
        ));
    }

    public List<User> getAllUsers() {return new ArrayList<>(users);}

    public boolean removeUserByEmail(String email) {
        return users.removeIf(u -> u.getEmail().equals(email));
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

    // public List<User> getAllUsers() {return users;}
    public void removeUser(User user) {users.remove(user);}
}
