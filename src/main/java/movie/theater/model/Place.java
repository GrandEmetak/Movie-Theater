package movie.theater.model;

/**
 * Модель данных описывающая - место в кинотеатре
 */
public class Place {

    private int id;
    private int row;
    private int cell;
    private boolean status;

    static public Place placeOf(int id, int row, int cell, boolean status) {
        Place place = new Place();
        place.id = id;
        place.row = row;
        place.cell = cell;
        place.status = status;
        return place;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Place{"
                + "id=" + id
                + ", row=" + row
                + ", cell=" + cell
                + ", place=" + status
                + '}';
    }
}
