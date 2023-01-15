package movie.theater.store;

import movie.theater.model.Account;
import movie.theater.model.Place;
import movie.theater.model.Ticket;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Основная модель хранилища
 */
public class PsqlStore implements Store {

    final static Logger LOGGER =
            LogManager.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();

    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    /**
     * show all places
     *
     * @return List Place
     */
    @Override
    public Collection<Place> findAllPlace() {
        List<Place> list = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM session ORDER BY id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    list.add(Place.placeOf(it.getInt("id"),
                            it.getInt("row"),
                            it.getInt("cell"),
                            it.getBoolean("status")));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return list;
    }

    /**
     * Добавление данных о выбранном месте в таблицу session
     *
     * @param row  ряд
     * @param cell место
     */
    @Override
    public void reservePlace(int row, int cell) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE session SET status = false where row = ? and cell = ?"
             )) {
            ps.setInt(1, row);
            ps.setInt(2, cell);
            ps.execute();
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO ticket(session_id, row, cell, account_id) VALUES(?, ?, ?, ?) "
                             + "ON CONFLICT DO NOTHING",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ticket.getSession());
            ps.setInt(2, ticket.getRow());
            ps.setInt(3, ticket.getCell());
            ps.setInt(4, ticket.getAccountId());
            ps.execute();
            try (ResultSet genKey = ps.getGeneratedKeys()) {
                if (genKey.next()) {
                    ticket.setId(genKey.getInt(1));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return ticket;
    }

    /**
     * put Object Account to DB table account
     *
     * @param account Object
     * @return Object Account which will be posted in to DB account
     */
    @Override
    public Account createAccount(Account account) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO account(username, email, phone) VALUES(?, ?, ?) "
                             + "ON CONFLICT DO NOTHING",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getPhone() + "");
            ps.execute();
            try (ResultSet genKey = ps.getGeneratedKeys()) {
                if (genKey.next()) {
                    account.setId(genKey.getInt(1));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return account;
    }

    @Override
    public Ticket getTicketByRowAndCell(int row, int cell) {
        Ticket ticket = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM ticket WHERE row = ? and cell = ?")) {
            ps.setInt(1, row);
            ps.setInt(2, cell);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ticket = Ticket.ticketOf(rs.getInt("id"),
                            rs.getInt("session_id"),
                            rs.getInt("row"),
                            rs.getInt("cell"),
                            rs.getInt("account_id"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return ticket;
    }

    @Override
    public Account findAccountByPhone(String email) {
        Account account = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM account WHERE phone = ?")) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account = Account.accountOf(rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            Integer.parseInt(rs.getString("phone")));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception: ", e);
        }
        return account;
    }

}
