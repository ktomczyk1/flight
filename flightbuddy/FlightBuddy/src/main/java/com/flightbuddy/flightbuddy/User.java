package com.flightbuddy.flightbuddy;

public class User
{
    public enum Role {USER, ADMIN}

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Role role;

    public User(String firstName, String lastName,
                String email, String phone,
                String password, Role role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getPhone() {return phone;}
    public Role getRole() {return role;}
}
