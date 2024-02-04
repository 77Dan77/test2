package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.ERole;
import kz.iitu.itse1908.daniyal.finalspring.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    Optional<Role> findById(Long aLong);

    Role findByName(ERole name);

    @Override
    <S extends Role> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Role> findAll();

}
