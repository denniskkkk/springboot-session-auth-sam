package dbx.zzzz.repository;
import dbx.zzzz.model.Testdbx;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestdbxPageRepository extends PagingAndSortingRepository<Testdbx, Long>{
	Page<Testdbx> findAll (Pageable pageable);
}
