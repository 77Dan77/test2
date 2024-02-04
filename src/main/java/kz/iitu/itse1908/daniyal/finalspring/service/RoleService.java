package kz.iitu.itse1908.daniyal.finalspring.service;

import kz.iitu.itse1908.daniyal.finalspring.models.ERole;
import kz.iitu.itse1908.daniyal.finalspring.models.Role;
import kz.iitu.itse1908.daniyal.finalspring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(Long aLong){
        return roleRepository.findById(aLong);
    }

    public <S extends Role> List<S> saveAll(Iterable<S> entities){
        return roleRepository.saveAll(entities);
    }

    public Role findByName(ERole name){
        return roleRepository.findByName(name);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
