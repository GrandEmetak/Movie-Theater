package ru.job4j.cinema.store;

import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.model.Place;
import ru.job4j.cinema.model.Session;
import ru.job4j.cinema.model.Ticket;

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

    void reservePlace(int placeId);

    Ticket createTicket(Ticket ticket);

    Ticket getTicketByRowAndCell(int row, int cell);

    Account createAccount(Account account);

    Account findAccountByPhone(String email);
}
