package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.ERole;
import kz.iitu.itse1908.daniyal.finalspring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Query("select u from User u inner join u.roles roles where roles.name = ?1")
    List<User> findByRoles_Name(ERole name);


}
