package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.repository.FineRepository;
import kz.iitu.itse1908.daniyal.finalspring.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @Test
    void findById() {
        ticketRepository.getById(Long.valueOf(1));
        verify(ticketRepository, times(1)).
                getById(Long.valueOf(1));
    }

    @Test
    void findAll() {
        ticketRepository.findAll();
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        verify(ticketRepository, never()).deleteById(any());
    }

    @Test
    void save() {
        ticketRepository.save(any());
        verify(ticketRepository, times(1)).save(any());
    }

    @Test
    void findByUserDetails_Id() {
        ticketRepository.findByUserDetails_Id(Long.valueOf(1));
        verify(ticketRepository, times(1)).
                findByUserDetails_Id(Long.valueOf(1));
    }

    @Test
    void findTicketsByFnameAndLname() {
        ticketRepository.findTicketsByFnameAndLname(anyString(), anyString());
        verify(ticketRepository,
                times(1)).
                findTicketsByFnameAndLname(anyString(), anyString());
    }

    @Test
    void getByUserDetails_Id() {
        ticketRepository.getByUserDetails_Id(Long.valueOf(1));
        verify(ticketRepository, times(1)).
                getByUserDetails_Id(Long.valueOf(1));
    }

    @Test
    void findByStatus() {
        ticketRepository.findByStatus(anyString());
        verify(ticketRepository,
                times(1)).
                findByStatus(anyString());
    }

    @Test
    void findByUserDetails_IdAndStatus() {
        ticketRepository.findByUserDetails_IdAndStatus(anyLong(), anyString());
        verify(ticketRepository,
                times(1)).
                findByUserDetails_IdAndStatus(anyLong(), anyString());
    }

    @Test
    void updateReceiveTimeAndDeadlineById() {
        ticketRepository.updateReceiveTimeAndDeadlineById(anyString(), anyString(),
                anyString(), anyLong());
        verify(ticketRepository, atMost(1)).updateReceiveTimeAndDeadlineById(anyString(),
                anyString(), anyString(), anyLong());
    }
}