package ru.job4j.cinema.model;

import java.util.Objects;

/**
 * Модель данных описывает пользователя.
 */
public class Account {
    private int id;
    private String userName;
    private String email;
    private int phone;

    public static  Account accountOf(int id, String userName, String email, int phone) {
        Account account = new Account();
        account.id = id;
        account.userName = userName;
        account.email = email;
        account.phone = phone;
        return account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id
                && phone == account.phone
                && Objects.equals(userName, account.userName)
                && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, phone);
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", userName='" + userName + '\''
                + ", email='" + email + '\''
                + ", phone=" + phone
                + '}';
    }
}
