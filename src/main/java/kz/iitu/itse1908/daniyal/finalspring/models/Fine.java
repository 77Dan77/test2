package kz.iitu.itse1908.daniyal.finalspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.envers.NotAudited;
import javax.persistence.*;

@Entity
@Table(name = "fines")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value= {"userDetails"})
public class Fine {
    private Long id;
    private String Description;
    private UserDetails userDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "description")
    public String getDescription() {
        return Description;
    }

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    @NotAudited
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public static Fine.FineBuilder fineBuilder() {
        return new Fine().new FineBuilder();
    }

    public class FineBuilder {
        private FineBuilder() {
        }

        public Fine.FineBuilder setId(Long id) {
            Fine.this.id = id;
            return this;
        }

        public Fine.FineBuilder setDescription(String Description) {
            Fine.this.Description = Description;
            return this;
        }

        public Fine.FineBuilder setUserDetails(UserDetails userDetails) {
            Fine.this.userDetails = userDetails;
            return this;
        }

        public Fine build() {
            return Fine.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
