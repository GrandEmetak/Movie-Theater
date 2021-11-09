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

/**
 * Сервлет получает данные о текущес состоянии мест кинотеатра (заняты/свободны)
 * places busy or free
 */
/*@WebServlet(urlPatterns = "/hall.do")*/
public class HallServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();
    private List<Place> placeList = new ArrayList<>();
    private Place[][] arrPlace = new Place[3][3];

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Place place : PsqlStore.instOf().findAllPlace()) {
            placeList.add(place);
        }

        int time = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrPlace[i][j] = placeList.get(time++);
            }
        }
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(arrPlace);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
