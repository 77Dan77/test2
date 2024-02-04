package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.Book;
import kz.iitu.itse1908.daniyal.finalspring.models.UserDetails;
import kz.iitu.itse1908.daniyal.finalspring.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class UserDetailsService {
    UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public List<UserDetails> findAll(){
        return userDetailsRepository.findAll();
    }

    public List<UserDetails> findByFname(String fname){
        return userDetailsRepository.findByFname(fname);
    }

    public List<UserDetails> findByLname(String lname){
        return userDetailsRepository.findByLname(lname);
    }

    public void deleteById(Long aLong){
        userDetailsRepository.deleteById(aLong);
    }

    public Optional<UserDetails> findById(Long aLong){
        return userDetailsRepository.findById(aLong);
    }

    public UserDetails findByFines_Id(Long id){
        return userDetailsRepository.findByFines_Id(id);
    }

    public UserDetails findByTickets_Id(Long id){
        return userDetailsRepository.findByTickets_Id(id);
    }

    public Optional<UserDetails> findByLnameAndFname(String lname, String fname){
        return userDetailsRepository.findByLnameAndFname(lname, fname);
    }

    @Transactional
    public int updateFnameAndLnameById(String fname, String lname, Long id){
        return userDetailsRepository.updateFnameAndLnameById(fname, lname, id);
    }

    public <S extends UserDetails> S save(S entity){
        return userDetailsRepository.save(entity);
    }

    public <S extends UserDetails> List<S> saveAll(Iterable<S> entities){
        return userDetailsRepository.saveAll(entities);
    }

}
