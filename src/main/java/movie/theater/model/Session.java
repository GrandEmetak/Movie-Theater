package movie.theater.model;

import java.util.Objects;

/**
 * Модель описывает Сеанс/move
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return id == session.id && Objects.equals(nameMove, session.nameMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMove);
    }

    @Override
    public String toString() {
        return "Session{"
                + "id=" + id
                + ", nameMove='"
                + nameMove + '\''
                + '}';
    }
}
