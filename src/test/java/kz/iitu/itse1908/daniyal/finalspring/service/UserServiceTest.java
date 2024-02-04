package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Test
    void findAll() {
        userRepository.findAll();
        verify(userRepository, times(1)).
                findAll();
    }

    @Test
    void save() {
        userRepository.save(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void findById() {
        userRepository.getById(Long.valueOf(1));
        verify(userRepository, times(1)).getById(Long.valueOf(1));
    }

    @Test
    void deleteById() {
        verify(userRepository, never()).deleteById(any());
    }
}