package ru.job4j.cinema.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cinema.model.Place;
import ru.job4j.cinema.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(urlPatterns = "/hall.do")
public class HallServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();
    List<Place> placeList = new ArrayList<>();
Place[][] arrPlace = new Place[3][3];
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("posts", PsqlStore.instOf().findAllPlace());
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("UTF-8");
//        String name = req.getParameter("name");
       // System.out.println("Вошли в Сервлет");
        //var list = PsqlStore.instOf().findAllPlace();
        for (Place place : PsqlStore.instOf().findAllPlace()) {
            System.out.println("Print Place : " + place);
            placeList.add(place);
        }
       // int index = placeList.size();
        int time = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrPlace[i][j] = placeList.get(time++);
            }
        }
//String string = "Hello, World!";
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(arrPlace);
//        String json = GSON.toJson(placeList);
//        String json = GSON.toJson(string);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
        System.out.println("вышли");

//        System.out.println("to xhto na knopku : " + name);
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.println("Nice to meet you, " + name);
//        writer.flush();
        //req.setAttribute("posts", PsqlStore.instOf().findAllPosts());
//        req.setAttribute("user", req.getSession().getAttribute("user"));
//        req.getRequestDispatcher("/payment.html").forward(req, resp);
//        resp.setContentType("application/json; charset=utf-8");
//        req.getSession().invalidate();
       //resp.sendRedirect(req.getContextPath() + "/payment.html");
    }
}
