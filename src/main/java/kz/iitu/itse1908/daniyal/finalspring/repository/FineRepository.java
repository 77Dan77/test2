package kz.iitu.itse1908.daniyal.finalspring.repository;

import kz.iitu.itse1908.daniyal.finalspring.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {

    @Override
    List<Fine> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Fine> S save(S entity);

    @Override
    Optional<Fine> findById(Long aLong);

    @Override
    <S extends Fine> List<S> saveAll(Iterable<S> entities);

    @Query("select distinct f from Fine f where f.userDetails.id = ?1")
    Fine findFineByUserDetails_Id(Long id);

    @Query("select f from Fine f where f.userDetails.lname = ?1 and f.userDetails.fname = ?2")
    List<Fine> findFineByLnameAndFname(String lname, String fname);

    @Query("select f from Fine f inner join f.userDetails.fines fines where fines.id = ?1")
    Fine findByUserDetails_Fines_Id(Long id);

    Fine findByUserDetails_Id(Long id);

    @Transactional
    @Modifying
    @Query("update Fine f set f.description = ?1 where f.id = ?2")
    int updateFineDescriptionById(String description, Long id);

}
