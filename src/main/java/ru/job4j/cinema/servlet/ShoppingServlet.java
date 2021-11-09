package ru.job4j.cinema.servlet;

import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет получает данные с страницы оплаты и заносит их в БД,
 * в таблицы Account, Session
 */
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var name1 = req.getParameter("user");
        var name2 = req.getParameter("phone");
        var name3 = req.getParameter("place");

        var arr = name3.split("\\.");

        PsqlStore.instOf().reservePlace(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        PsqlStore.instOf().createAccount(new Account(
                name1, name1, Long.parseLong(name2))
        );
    }
}
