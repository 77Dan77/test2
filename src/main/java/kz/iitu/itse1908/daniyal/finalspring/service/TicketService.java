package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.Ticket;
import kz.iitu.itse1908.daniyal.finalspring.models.UserDetails;
import kz.iitu.itse1908.daniyal.finalspring.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class TicketService {
    TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> findById(Long aLong){
        return ticketRepository.findById(aLong);
    }

    public List<Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public void deleteById(Long aLong){
        ticketRepository.deleteById(aLong);
    }

    public <S extends Ticket> S save(S entity){
        return ticketRepository.save(entity);
    }

    public List<Ticket> findByUserDetails_Id(Long id){
        return ticketRepository.findByUserDetails_Id(id);
    }

    public List<Ticket> findTicketsByFnameAndLname(String lname, String fname){
        return ticketRepository.findTicketsByFnameAndLname(lname, fname);
    }

    public Ticket getByUserDetails_Id(Long id){
        return ticketRepository.getByUserDetails_Id(id);
    }

    public <S extends Ticket> List<S> saveAll(Iterable<S> entities){
        return ticketRepository.saveAll(entities);
    }

    public List<Ticket> findByStatus(String status){
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> findByUserDetails_IdAndStatus(Long id, String status){
        return ticketRepository.findByUserDetails_IdAndStatus(id, status);
    }

    @Transactional
    public int updateReceiveTimeAndDeadlineById(String status, String receiveTime, String deadline, Long id){
        return ticketRepository.updateReceiveTimeAndDeadlineById(status, receiveTime, deadline, id);
    }

}
