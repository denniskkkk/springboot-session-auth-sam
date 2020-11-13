package dbx.zzzz.repository;

import org.springframework.data.repository.CrudRepository;

import dbx.zzzz.model.Testdbx;

import java.util.List;

public interface TestdbxRepository extends CrudRepository <Testdbx, Long> {
    List <Testdbx> findAll();	
    List <Testdbx> findAllByName(String name);
    List <Testdbx> findByIdx (Long idx);    
    List <Testdbx> findByName (String name);
    List <Testdbx> findByAddress (String address);
}
