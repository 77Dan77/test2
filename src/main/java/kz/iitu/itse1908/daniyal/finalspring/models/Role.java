package kz.iitu.itse1908.daniyal.finalspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public static Role.RoleBuilder fineBuilder() {
        return new Role().new RoleBuilder();
    }

    public class RoleBuilder {
        private RoleBuilder() {
        }

        public Role.RoleBuilder setId(Long id) {
            Role.this.id = id;
            return this;
        }

        public Role.RoleBuilder setName(ERole name) {
            Role.this.name = name;
            return this;
        }

        public Role build() {
            return Role.this;
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
