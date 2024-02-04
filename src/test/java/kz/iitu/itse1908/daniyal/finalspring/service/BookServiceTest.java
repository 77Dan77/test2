package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Test
    void findAll() {
        bookRepository.findAll();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        bookRepository.getById(Long.valueOf(1));
        verify(bookRepository, times(1)).
                getById(Long.valueOf(1));
    }

    @Test
    void findByName() {
        bookRepository.findByName("dan");
        bookRepository.findByName("dan");
        verify(bookRepository, atMost(2)).findByName("dan");
    }

    @Test
    void findByAuthor() {
        bookRepository.findByAuthor("Rowling");
        verify(bookRepository, atMost(1)).findByAuthor("dan");
    }

    @Test
    void save() {
        bookRepository.save(any());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void deleteById() {
        verify(bookRepository, never()).deleteById(any());
    }

    @Test
    void findByTicket_Id() {
        bookRepository.findByTicket_Id(Long.valueOf(1));
        verify(bookRepository, atMost(1)).
                findByTicket_Id(Long.valueOf(1));
    }

    @Test
    void findBookByTicket_UserDetails_Id() {
        bookRepository.findBookByTicket_UserDetails_Id(Long.valueOf(1));
        verify(bookRepository, times(1)).
                findBookByTicket_UserDetails_Id(Long.valueOf(1));
    }

    @Test
    void updateBookById() {
        bookRepository.updateBookById(anyString(),
                anyString(), anyString(), anyInt(), anyString(), anyLong(), anyLong());
        verify(bookRepository, atMost(1)).updateBookById(anyString(),
                anyString(), anyString(), anyInt(), anyString(), anyLong(), anyLong());
    }
}