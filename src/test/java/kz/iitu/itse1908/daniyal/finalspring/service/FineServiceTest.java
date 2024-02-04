package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.repository.BookRepository;
import kz.iitu.itse1908.daniyal.finalspring.repository.FineRepository;
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
class FineServiceTest {

    @Mock
    FineRepository fineRepository;

    @Test
    void findAll() {
        fineRepository.findAll();
        verify(fineRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        verify(fineRepository, never()).deleteById(any());
    }

    @Test
    void save() {
        fineRepository.save(any());
        verify(fineRepository, times(1)).save(any());
    }

    @Test
    void findById() {
        fineRepository.getById(Long.valueOf(1));
        verify(fineRepository, times(1)).
                getById(Long.valueOf(1));
    }

    @Test
    void findFineByUserDetails_Id() {
        fineRepository.findFineByUserDetails_Id(Long.valueOf(1));
        verify(fineRepository, times(1)).
                findFineByUserDetails_Id(Long.valueOf(1));
    }

    @Test
    void findFineByLnameAndFname() {
        fineRepository.findFineByLnameAndFname(anyString(), anyString());
        verify(fineRepository,
                times(1)).
                findFineByLnameAndFname(anyString(), anyString());
    }

    @Test
    void findByUserDetails_Id() {
        fineRepository.findByUserDetails_Id(Long.valueOf(1));
        verify(fineRepository, times(1)).
                findByUserDetails_Id(Long.valueOf(1));
    }

    @Test
    void updateFineDescriptionById() {
        fineRepository.updateFineDescriptionById(anyString(), anyLong());
        verify(fineRepository, atMost(1)).updateFineDescriptionById(anyString(), anyLong());
    }
}