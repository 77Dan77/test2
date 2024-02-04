package kz.iitu.itse1908.daniyal.finalspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.envers.NotAudited;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@JsonIgnoreProperties(value= {"ticket"})
public class Book {
    private Long id;
    private String name;
    private String author;
    private String genre;
    private int pages;
    private String cover;
    private Long stock;
    private Ticket ticket;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    @Column(name = "pages")
    public int getPages() {
        return pages;
    }

    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    @Column(name = "stock")
    public Long getStock() {
        return stock;
    }

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    @NotAudited
    public Ticket getTicket() {
        return ticket;
    }

    public static BookBuilder bookBuilder() {
        return new Book().new BookBuilder();
    }

    public class BookBuilder {
        private BookBuilder() {
        }


        public BookBuilder setId(Long id) {
            Book.this.id = id;
            return this;
        }

        public BookBuilder setName(String name) {
            Book.this.name = name;
            return this;
        }

        public BookBuilder setAuthor(String author) {
            Book.this.author = author;
            return this;
        }

        public BookBuilder setGenre(String genre){
            Book.this.genre = genre;
            return this;
        }

        public BookBuilder setPages(int pages) {
            Book.this.pages = pages;
            return this;
        }

        public BookBuilder setCover(String cover) {
            Book.this.cover = cover;
            return this;
        }

        public BookBuilder setStock(Long stock) {
            Book.this.stock = stock;
            return this;
        }

        public BookBuilder setTicket(Ticket ticket) {
            Book.this.ticket = ticket;
            return this;
        }

        public Book build() {
            return Book.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", pages=" + pages +
                ", cover='" + cover + '\'' +
                ", stock=" + stock + " " + ticket +
                '}';
    }

    public String toStringName() {
        return  " \"" + name + "\" ";
    }

}
