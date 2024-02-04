package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.repository.TicketRepository;
import kz.iitu.itse1908.daniyal.finalspring.repository.UserDetailsRepository;
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
class UserDetailsServiceTest {

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Test
    void findAll() {
        userDetailsRepository.findAll();
        verify(userDetailsRepository, times(1)).
                findAll();
    }

    @Test
    void findByFname() {
        userDetailsRepository.findByFname("dan");
        verify(userDetailsRepository, times(1)).
                findByFname("dan");
    }

    @Test
    void findByLname() {
        userDetailsRepository.findByLname("zhexenov");
        verify(userDetailsRepository, times(1)).findByLname("zhexenov");
    }

    @Test
    void deleteById() {
        verify(userDetailsRepository, never()).deleteById(any());
    }

    @Test
    void findById() {
        userDetailsRepository.getById(Long.valueOf(1));
        verify(userDetailsRepository, times(1)).
                getById(Long.valueOf(1));
    }

    @Test
    void findByFines_Id() {
        userDetailsRepository.findByFines_Id(Long.valueOf(1));
        verify(userDetailsRepository, times(1)).
                findByFines_Id(Long.valueOf(1));
    }

    @Test
    void findByTickets_Id() {
        userDetailsRepository.
                findByTickets_Id(Long.valueOf(1));
        verify(userDetailsRepository, times(1)).
                findByTickets_Id(Long.valueOf(1));
    }

    @Test
    void findByLnameAndFname() {
        userDetailsRepository.findByLnameAndFname(anyString(), anyString());
        verify(userDetailsRepository,
                times(1)).
                findByLnameAndFname(anyString(), anyString());
    }

    @Test
    void updateFnameAndLnameById() {
        userDetailsRepository.updateFnameAndLnameById(anyString(), anyString(), anyLong());
        verify(userDetailsRepository, atMost(1)).updateFnameAndLnameById(anyString(),
                anyString(), anyLong());
    }

    @Test
    void save() {
        userDetailsRepository.save(any());
        verify(userDetailsRepository, times(1)).save(any());
    }

}