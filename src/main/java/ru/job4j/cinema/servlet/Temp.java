package ru.job4j.cinema.servlet;

import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.store.PsqlStore;

public class Temp {
    public static void main(String[] args) {
        var name1 = "JohnSmith@post.com";
        var name2 = "12095557545";
        var name3 = "3.3";
        System.out.println("Parse " + Long.parseLong(name2));
        var arr = name3.split("\\.");

        PsqlStore.instOf().reservePlace(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        PsqlStore.instOf().createAccount(new Account(
                name1, name1, Long.parseLong(name2))
        );
    }
}
