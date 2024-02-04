package kz.iitu.itse1908.daniyal.finalspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.envers.NotAudited;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"userDetails"})
public class Ticket {
    private Long id;
    private UserDetails userDetails;
    private String receiveTime;
    private String deadline;
    private String status;
    private Set<Book> bookList = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    @Column(name = "recieve_time")
    public String getReceiveTime() {
        return receiveTime;
    }

    @Column(name = "deadline")
    public String getDeadline() {
        return deadline;
    }

    @OneToMany(mappedBy = "ticket")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public Set<Book> getBookList() {
        return bookList;
    }

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    @NotAudited
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public static Ticket.TicketBuilder ticketBuilder() {
        return new Ticket().new TicketBuilder();
    }

    public class TicketBuilder {
        private TicketBuilder() {
        }

        public Ticket.TicketBuilder setId(Long id) {
            Ticket.this.id = id;
            return this;
        }

        public Ticket.TicketBuilder setDeadline(String deadline) {
            Ticket.this.deadline = deadline;
            return this;
        }

        public Ticket.TicketBuilder setReceieveTime(String receiveTime) {
            Ticket.this.receiveTime = receiveTime;
            return this;
        }

        public Ticket.TicketBuilder setBooks(Set<Book> bookList) {
            Ticket.this.bookList = bookList;
            return this;
        }

        public Ticket.TicketBuilder setStatus(String status) {
            Ticket.this.status = status;
            return this;
        }

        public Ticket.TicketBuilder setUserDetails(UserDetails userDetails) {
            Ticket.this.userDetails = userDetails;
            return this;
        }

        public Ticket build() {
            return Ticket.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userDetails=" + userDetails +
                ", receiveTime=" + receiveTime +
                ", deadline=" + deadline +
                ", status=" + status +
//                ", books=" + bookList +
                '}';
    }
}
