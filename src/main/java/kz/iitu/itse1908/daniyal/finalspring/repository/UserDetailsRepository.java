package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    @Override
    List<UserDetails> findAll();

    @Query("select u from UserDetails u where u.fname = ?1")
    List<UserDetails> findByFname(String fname);

    @Query("select u from UserDetails u where u.lname = ?1")
    List<UserDetails> findByLname(String lname);

    @Override
    void deleteById(Long aLong);

    @Query("select u from UserDetails u inner join u.fines fines where fines.id = ?1")
    UserDetails findByFines_Id(Long id);

    @Query("select u from UserDetails u inner join u.tickets tickets where tickets.id = ?1")
    UserDetails findByTickets_Id(Long id);

    @Query("select u from UserDetails u where u.lname = ?1 and u.fname = ?2")
    Optional<UserDetails> findByLnameAndFname(String lname, String fname);

    @Override
    Optional<UserDetails> findById(Long aLong);

    @Transactional
    @Modifying
    @Query("update UserDetails u set u.fname = ?1, u.lname = ?2 where u.id = ?3")
    int updateFnameAndLnameById(String fname, String lname, Long id);

    @Override
    <S extends UserDetails> S save(S entity);

    @Override
    <S extends UserDetails> List<S> saveAll(Iterable<S> entities);
}
