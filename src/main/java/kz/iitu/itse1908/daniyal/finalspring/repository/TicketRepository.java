package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    Optional<Ticket> findById(Long aLong);

    @Override
    List<Ticket> findAll();



    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Ticket> S save(S entity);

    @Query("select t from Ticket t where t.userDetails.id = ?1")
    Ticket getByUserDetails_Id(Long id);

    @Query("select t from Ticket t where t.userDetails.id = ?1")
    List<Ticket> findByUserDetails_Id(Long id);

    @Query("select t from Ticket t where t.userDetails.lname = ?1 and t.userDetails.fname = ?2")
    List<Ticket> findTicketsByFnameAndLname(String lname, String fname);

    @Transactional
    @Modifying
    @Query("update Ticket t set t.status = ?1, t.receiveTime = ?2, t.deadline = ?3 where t.id = ?4")
    int updateReceiveTimeAndDeadlineById(String status, String receiveTime, String deadline, Long id);

    @Query("select t from Ticket t where t.status = ?1")
    List<Ticket> findByStatus(String status);

    @Query("select t from Ticket t where t.userDetails.id = ?1 and t.status = ?2")
    List<Ticket> findByUserDetails_IdAndStatus(Long id, String status);



    @Override
    <S extends Ticket> List<S> saveAll(Iterable<S> entities);

//    @Transactional
//    @Modifying
//    @Query("update Ticket t set t.receiveTime = ?1, t.deadline = ?2 where t.id = ?3")
//    int updateReceiveTimeAndDeadlineById(String receiveTime, String deadline, Long id);

}
