package movie.theater.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import movie.theater.model.Place;
import movie.theater.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет получает данные о текущем состоянии мест кинотеатра (заняты/свободны)
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

        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(placeList);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
