package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.Fine;
import kz.iitu.itse1908.daniyal.finalspring.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class FineService {
    FineRepository fineService;

    @Autowired
    public FineService(FineRepository fineService) {
        this.fineService = fineService;
    }

    public List<Fine> findAll(){
        return fineService.findAll();
    }

    public void deleteById(Long aLong){
        fineService.deleteById(aLong);
    }

    public <S extends Fine> S save(S entity){
        return fineService.save(entity);
    }

    public <S extends Fine> List<S> saveAll(Iterable<S> entities){
        return fineService.saveAll(entities);
    }

    public Optional<Fine> findById(Long aLong){
        return fineService.findById(aLong);
    }

    public Fine findFineByUserDetails_Id(Long id){
        return fineService.findFineByUserDetails_Id(id);
    }

    public List<Fine> findFineByLnameAndFname(String lname, String fname){
        return fineService.findFineByLnameAndFname(lname, fname);
    }

    public Fine findByUserDetails_Id(Long id){
        return fineService.findByUserDetails_Id(id);
    }

    @Transactional
    public int updateFineDescriptionById(String description, Long id){
        return fineService.updateFineDescriptionById(description, id);
    }
}
