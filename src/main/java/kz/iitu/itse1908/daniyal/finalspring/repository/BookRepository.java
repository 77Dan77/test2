package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.Book;
import kz.iitu.itse1908.daniyal.finalspring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long aLong);

    @Query("select b from Book b where b.name = ?1")
    Book findByName(String name);

    @Query("select b from Book b where b.author = ?1")
    Book findByAuthor(String author);

    @Query("select b from Book b where b.genre = ?1")
    Book findByGenre(String genre);

    @Query("select b from Book b where b.genre = ?1")
    List<Book> getByGenre(String genre);

    @Override
    <S extends Book> S save(S entity);

    @Override
    <S extends Book> List<S> saveAll(Iterable<S> entities);

    @Override
    void deleteById(Long aLong);

    @Query("select b from Book b where b.ticket.userDetails.id = ?1")//найти список книг которые получил юзер с определенным айди
    List<Book> findBookByTicket_UserDetails_Id(Long id);

    @Query("select b from Book b where b.ticket.id = ?1")
    List<Book> findByTicket_Id(Long id);

    @Transactional
    @Modifying
    @Query("update Book b set b.name = ?1, b.author = ?2, b.genre = ?3, b.pages = ?4, b.cover = ?5, b.stock = ?6 " +
            "where b.id = ?7")
    int updateBookById(String name, String author, String genre, int pages, String cover, Long stock, Long id);

}
