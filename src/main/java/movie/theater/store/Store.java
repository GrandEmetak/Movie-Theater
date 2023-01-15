package movie.theater.store;

import movie.theater.model.Account;
import movie.theater.model.Ticket;
import movie.theater.model.Place;

import java.util.Collection;


public interface Store {

    Collection<Place> findAllPlace();

    void reservePlace(int row, int cell);

    Ticket createTicket(Ticket ticket);

    Ticket getTicketByRowAndCell(int row, int cell);

    Account createAccount(Account account);

    Account findAccountByPhone(String email);
}
