package movie.theater.store;

import movie.theater.model.Account;
import movie.theater.model.Ticket;
import movie.theater.model.Place;

import java.util.Collection;

/**
 * Расширьте интерфейс Store.
 * Добавьте методы для работы с классом User.
 * сохранение и поиск по email
 * @author SlartiBartFast-art
 * @since 03.11.21
 */
public interface Store {
    Collection<Place> findAllPlace();

    void reservePlace(int row, int cell);

    Ticket createTicket(Ticket ticket);

    Ticket getTicketByRowAndCell(int row, int cell);

    Account createAccount(Account account);

    Account findAccountByPhone(String email);
}
