package kz.iitu.itse1908.daniyal.finalspring;

import kz.iitu.itse1908.daniyal.finalspring.models.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PrepareData {
    @Autowired
    JdbcTemplate jdbcTemplate;
    SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createTables()  {
        log.info("Creating tables for testing...");
        jdbcTemplate.execute("DROP TABLE IF EXISTS user_roles");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("DROP TABLE IF EXISTS fines");
        jdbcTemplate.execute("DROP TABLE IF EXISTS books");
        jdbcTemplate.execute("DROP TABLE IF EXISTS tickets");
        jdbcTemplate.execute("DROP TABLE IF EXISTS user_details");
        jdbcTemplate.execute("DROP TABLE IF EXISTS roles");

        jdbcTemplate.execute("CREATE TABLE roles\n" +
                "(\n" +
                "    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    name VARCHAR(20),\n" +
                "    CONSTRAINT pk_roles PRIMARY KEY (id)\n" +
                ");");

        jdbcTemplate.execute("CREATE TABLE user_details\n" +
                "(\n" +
                "    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    fname VARCHAR(255),\n" +
                "    lname VARCHAR(255),\n" +
                "    CONSTRAINT pk_user_details PRIMARY KEY (id)\n" +
                ");");

        jdbcTemplate.execute("CREATE TABLE tickets\n" +
                "(\n" +
                "    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    recieve_time    VARCHAR(255),\n" +
                "    deadline        VARCHAR(255),\n" +
                "    status        VARCHAR(255),\n" +
                "    user_details_id BIGINT,\n" +
                "    CONSTRAINT pk_tickets PRIMARY KEY (id)\n" +
                ");");
        jdbcTemplate.execute("ALTER TABLE tickets\n" +
                "    ADD CONSTRAINT FK_TICKETS_ON_USER_DETAILS FOREIGN KEY (user_details_id) REFERENCES user_details (id);");


        jdbcTemplate.execute("CREATE TABLE books\n" +
                "(\n" +
                "    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    name      VARCHAR(255),\n" +
                "    author    VARCHAR(255),\n" +
                "    genre     VARCHAR(255),\n" +
                "    pages     INTEGER,\n" +
                "    cover     VARCHAR(255),\n" +
                "    stock     BIGINT,\n" +
                "    ticket_id BIGINT,\n" +
                "    CONSTRAINT pk_books PRIMARY KEY (id)\n" +
                ");");
        jdbcTemplate.execute("ALTER TABLE books\n" +
                "    ADD CONSTRAINT FK_BOOKS_ON_TICKET FOREIGN KEY (ticket_id) REFERENCES tickets (id);");


        jdbcTemplate.execute("CREATE TABLE fines\n" +
                "(\n" +
                "    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    description     VARCHAR(255),\n" +
                "    user_details_id BIGINT,\n" +
                "    CONSTRAINT pk_fines PRIMARY KEY (id)\n" +
                ");");
        jdbcTemplate.execute("ALTER TABLE fines\n" +
                "    ADD CONSTRAINT FK_FINES_ON_USER_DETAILS FOREIGN KEY (user_details_id) REFERENCES user_details (id);\n");


        jdbcTemplate.execute("CREATE TABLE users\n" +
                "(\n" +
                "    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,\n" +
                "    user_deatails_id BIGINT,\n" +
                "    CONSTRAINT pk_users PRIMARY KEY (id)\n" +
                ");");
        jdbcTemplate.execute("ALTER TABLE users\n" +
                "    ADD CONSTRAINT FK_USERS_ON_USER_DEATAILS FOREIGN KEY (user_deatails_id) REFERENCES user_details (id);");


        jdbcTemplate.execute("CREATE TABLE user_roles\n" +
                "(\n" +
                "    user_id               BIGINT,\n" +
                "    role_id BIGINT \n"+
                ");");
        jdbcTemplate.execute("ALTER TABLE user_roles\n" +
                "    ADD CONSTRAINT FK_USERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);");
        jdbcTemplate.execute("ALTER TABLE user_roles\n" +
                "    ADD CONSTRAINT FK_USERS_ON_ROLES FOREIGN KEY (role_id) REFERENCES roles (id);");
    }

    public List<Book> BookList() throws Exception {
        ArrayList<Book> books = new ArrayList<>();
        books.add(Book.bookBuilder().setId(Long.valueOf(1)).setName("Harry Potter").setAuthor("J.K.Rowling").
                setGenre("Fantasy").setPages(643).setCover("Hardcover").setStock(Long.valueOf(23)).setTicket(ticketList().get(1)).build());
        books.add(Book.bookBuilder().setId(Long.valueOf(2)).setName("Lord of The Rings").setAuthor("Tolkien").
                setGenre("Fantasy").setPages(835).setCover("Hardcover").setStock(Long.valueOf(16)).setTicket(ticketList().get(0)).build());
        books.add(Book.bookBuilder().setId(Long.valueOf(3)).setName("The Chronicles of Narnia").setAuthor("C.S.Lewis").
                setGenre("Fantasy").setPages(587).setCover("Hardcover").setStock(Long.valueOf(10)).setTicket(ticketList().get(2)).build());
        return books;
    }


    public List<Fine> fineList() throws Exception {
        ArrayList<Fine> fines = new ArrayList<>();
        fines.add(Fine.fineBuilder().setId(Long.valueOf(1)).setDescription("Broke the book").setUserDetails(userDetailsList().get(0)).build());
        fines.add(Fine.fineBuilder().setId(Long.valueOf(2)).setDescription("Bad guy").setUserDetails(userDetailsList().get(0)).build());
        fines.add(Fine.fineBuilder().setId(Long.valueOf(3)).setDescription("Has been overdue").setUserDetails(userDetailsList().get(2)).build());
        return fines;
    }

    public List<Role> roleList() throws Exception {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(Role.fineBuilder().setId(Long.valueOf(1)).setName(ERole.ROLE_ADMIN).build());
        roles.add(Role.fineBuilder().setId(Long.valueOf(2)).setName(ERole.ROLE_CLIENT).build());
        return roles;
    }

    public List<Ticket> ticketList() throws Exception {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(Ticket.ticketBuilder().setId(Long.valueOf(1)).setDeadline("2022.05.17 03:02:06").setReceieveTime("2022.05.17 02:02:06").setUserDetails(userDetailsList().get(0)).build());
        tickets.add(Ticket.ticketBuilder().setId(Long.valueOf(2)).setDeadline("2022.05.17 03:02:06").setReceieveTime("2022.05.17 02:02:06").setUserDetails(userDetailsList().get(1)).build());
        tickets.add(Ticket.ticketBuilder().setId(Long.valueOf(3)).setDeadline("2022.05.17 03:02:06").setReceieveTime("2022.05.17 02:02:06").setUserDetails(userDetailsList().get(2)).build());
        return tickets;
    }

    public List<User> userList() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        users.add(User.userBuilder().setId(Long.valueOf(1)).setUserDetails(userDetailsList().get(0)).setRoles(roleList()).build());
        users.add(User.userBuilder().setId(Long.valueOf(2)).setUserDetails(userDetailsList().get(1)).setRoles(roleList()).build());
        users.add(User.userBuilder().setId(Long.valueOf(3)).setUserDetails(userDetailsList().get(2)).setRoles(roleList()).build());
        return users;
    }

    public List<UserDetails> userDetailsList() throws Exception {
        ArrayList<UserDetails> userDetails = new ArrayList<>();
        userDetails.add(UserDetails.userDetailsBuilder().setId(Long.valueOf(1)).setFname("Daniyal").setLname("Zhexenov").build());
        userDetails.add(UserDetails.userDetailsBuilder().setId(Long.valueOf(2)).setFname("Nurbol").setLname("Kalzhigitov").build());
        userDetails.add(UserDetails.userDetailsBuilder().setId(Long.valueOf(3)).setFname("Alexandr").setLname("Nevsky").build());
        return userDetails;
    }



}
