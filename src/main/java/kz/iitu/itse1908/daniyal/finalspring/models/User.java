package kz.iitu.itse1908.daniyal.finalspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value= {"userDetails"})
public class User {
    private Long id;
    private UserDetails userDetails;
    private List<Role> roles = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }


    @OneToOne
    @JoinColumn(name = "user_deatails_id", referencedColumnName = "id")
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public static User.UserBuilder userBuilder() {
        return new User().new UserBuilder();
    }

    public class UserBuilder {
        private UserBuilder() {
        }

        public User.UserBuilder setId(Long id) {
            User.this.id = id;
            return this;
        }

        public User.UserBuilder setUserDetails(UserDetails userDetails) {
            User.this.userDetails = userDetails;
            return this;
        }

        public User.UserBuilder setRoles(List<Role> roles) {
            User.this.roles = roles;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userDetails=" + userDetails +
                ", roles="  +
                '}';
    }
}
