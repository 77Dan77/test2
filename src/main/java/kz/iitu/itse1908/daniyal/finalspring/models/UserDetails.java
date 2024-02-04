package kz.iitu.itse1908.daniyal.finalspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_details")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value= {"user"})
public class UserDetails {
    private Long id;
    private String fname;
    private String lname;
    private Set<Ticket> tickets = new HashSet<>();
    private Set<Fine> fines = new HashSet<>();
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "fname")
    public String getFname() {
        return fname;
    }

    @Column(name = "lname")
    public String getLname() {
        return lname;
    }

    @OneToMany(mappedBy = "userDetails")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public Set<Ticket> getTickets() {
        return tickets;
    }

    @OneToMany(mappedBy = "userDetails")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public Set<Fine> getFines() {
        return fines;
    }

    @OneToOne(mappedBy = "userDetails")
    public User getUser() {
        return user;
    }

    public static UserDetails.UserDetailsBuilder userDetailsBuilder() {
        return new UserDetails().new UserDetailsBuilder();
    }

    public class UserDetailsBuilder {
        private UserDetailsBuilder() {
        }

        public UserDetails.UserDetailsBuilder setId(Long id) {
            UserDetails.this.id = id;
            return this;
        }

        public UserDetails.UserDetailsBuilder setFname(String fname) {
            UserDetails.this.fname = fname;
            return this;
        }

        public UserDetails.UserDetailsBuilder setLname(String lname) {
            UserDetails.this.lname = lname;
            return this;
        }

        public UserDetails.UserDetailsBuilder setTickets(Set<Ticket> tickets) {
            UserDetails.this.tickets = tickets;
            return this;
        }

        public UserDetails.UserDetailsBuilder setFines(Set<Fine> fines) {
            UserDetails.this.fines = fines;
            return this;
        }

        public UserDetails.UserDetailsBuilder setUsers(User user) {
            UserDetails.this.user = user;
            return this;
        }

        public UserDetails build() {
            return UserDetails.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setFines(Set<Fine> fines) {
        this.fines = fines;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' + user + '}';
    }
}
