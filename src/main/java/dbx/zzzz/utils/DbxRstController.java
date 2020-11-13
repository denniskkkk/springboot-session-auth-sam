package dbx.zzzz.utils;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbx.zzzz.model.Testdbx;
import dbx.zzzz.repository.TestdbxPageRepository;
import dbx.zzzz.repository.TestdbxRepository;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = { "http://localhost", "http://localhost:9000" })
public class DbxRstController {
    private final Logger log = LoggerFactory.getLogger(DbxRstController.class);
	private TestdbxRepository repos;
	private TestdbxPageRepository pagerepos;
    public DbxRstController (TestdbxRepository repos, TestdbxPageRepository pagerepos) {
        this.repos = repos;
        this.pagerepos = pagerepos;
    }	

    @GetMapping("/list")
    Collection<Testdbx> dbxList() {
        return (Collection<Testdbx>) repos.findAll();
    }
    
    @GetMapping("/list/{pageno}/{pagesize}")
    Collection<Testdbx> dbxList(@PathVariable int pageno, @PathVariable int pagesize) {
    	Pageable paging = PageRequest.of(pageno,pagesize, Sort.by ("idx").descending().and(Sort.by("idx")));
    	Page <Testdbx> pageresult = pagerepos.findAll (paging);
    	List <Testdbx> list = pageresult.toList();
    	return list;
    }    
    
    @GetMapping("/list/{id}")
    ResponseEntity<?> getDbx(@PathVariable Long id) {
        Optional<Testdbx> item = repos.findById(id);
        return item.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/list")
    ResponseEntity<Testdbx> createDbx(@Valid @RequestBody Testdbx dbx) throws URISyntaxException {
        log.info("create:", dbx);
        Testdbx result = repos.save(dbx);
        return ResponseEntity.created(new URI("/api/group/" + result.getIdx())).body(result);
    }

    @PutMapping("/list/{id}")
    ResponseEntity<Testdbx> updateGroup(@Valid @RequestBody Testdbx dbx) {
        log.info("update:", dbx);
        Testdbx result = repos.save(dbx);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/list/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("delete:", id);
        repos.deleteById(id);
        return ResponseEntity.ok().build();
    }    
}
