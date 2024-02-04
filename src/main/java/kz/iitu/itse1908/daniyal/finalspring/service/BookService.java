package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.Book;
import kz.iitu.itse1908.daniyal.finalspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

   public  <S extends Book> List<S> saveAll(Iterable<S> entities){
        return bookRepository.saveAll(entities);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long aLong){
        return bookRepository.findById(aLong);
    }

    public Book findByName(String name){
        return bookRepository.findByName(name);
    }

    public Book findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public Book findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }

    public <S extends Book> S save(S entity){
        return bookRepository.save(entity);
    }

    public void deleteById(Long aLong){
        bookRepository.deleteById(aLong);
    }

    public List<Book> getByGenre(String genre){
        return bookRepository.getByGenre(genre);
    }

    public List<Book> findByTicket_Id(Long id){
        return bookRepository.findByTicket_Id(id);
    }

   //найти список книг которые получил юзер с определенным айди
   public List<Book> findBookByTicket_UserDetails_Id(Long id){
        return bookRepository.findBookByTicket_UserDetails_Id(id);
    }

    @Transactional
    public int updateBookById(String name, String author, String genre, int pages, String cover, Long stock, Long id){
        return bookRepository.updateBookById(name, author, genre, pages, cover, stock, id);
    }

}
