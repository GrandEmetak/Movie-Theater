package ru.job4j.cinema.servlet;

import ru.job4j.cinema.store.PsqlStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлеты страницы полаты. передает выбранное ряд/место
 */
//@WebServlet(urlPatterns = "/oay.do")
public class PaymentServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("UTF-8");
//        String name = req.getParameter("name");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.println("Nice to meet you, " + name);
//        writer.flush();
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Вошли в сервлет");
//        var point =  req.getSession().getAttribute("user");
//        System.out.println("ТО что пришло : " + point);
//       // req.setAttribute("posts", PsqlStore.instOf().findAllPosts());
//        req.setAttribute("user", req.getSession().getAttribute("user"));
//        req.getRequestDispatcher("payment.html").forward(req, resp);
//        System.out.println("Вышли из сервлета");
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
       resp.setCharacterEncoding("UTF-8");

        System.out.println("Вошли в сервлет Payment");
        String name = req.getParameter("user");
        System.out.println("ТО что пришло : " + name);

        req.setAttribute("user", req.getParameter("user"));

        String path = "/payment.html";


        req.getRequestDispatcher(path).forward(req, resp);

//        String path = "/payment.html";
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        //requestDispatcher.forward(req, resp);

//        req.getRequestDispatcher("payment.html").forward(req, resp);
       System.out.println("Вышли из сервлета");
    }
}
