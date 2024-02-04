package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.ERole;
import kz.iitu.itse1908.daniyal.finalspring.models.User;
import kz.iitu.itse1908.daniyal.finalspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public <S extends User> S save(S entity){
        return userRepository.save(entity);
    }

    public <S extends User> List<S> saveAll(Iterable<S> entities){
        return userRepository.saveAll(entities);
    }

    public Optional<User> findById(Long aLong){
        return userRepository.findById(aLong);
    }

    public void deleteById(Long aLong){
        userRepository.deleteById(aLong);
    }

    public List<User> findByRoles_Name(ERole name){
        return userRepository.findByRoles_Name(name);
    }

}
