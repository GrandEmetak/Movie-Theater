package ru.job4j.cinema.model;

/**
 * Сеанс
 */
public class Session {
    private int id;
    private String nameMove;

    public Session(int id, String nameMove) {
        this.id = id;
        this.nameMove = nameMove;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMove() {
        return nameMove;
    }

    public void setNameMove(String nameMove) {
        this.nameMove = nameMove;
    }
}
