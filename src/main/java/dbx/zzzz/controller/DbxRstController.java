package dbx.zzzz.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/data")
@CrossOrigin(origins = { "http://localhost", "http://localhost:3000" }, maxAge=3600)
//allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"} )
public class DbxRstController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;   
	
    //private final Logger log = LoggerFactory.getLogger(DbxRstController.class);
	@Autowired
	private TestdbxRepository repos;
	@Autowired
	private TestdbxPageRepository pagerepos;
	
    public DbxRstController (/*TestdbxRepository repos, TestdbxPageRepository pagerepos*/) {
       // this.repos = repos;
       // this.pagerepos = pagerepos;
    }	

    @GetMapping("/list")
    //@RolesAllowed({"USER","ADMIN"})    
    Collection<Testdbx> dbxList() {
    	//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	//String currentPrincipalName = authentication.getName();
    	//Boolean currentRole = authentication.isAuthenticated();
    	//log.info("current user=" + currentPrincipalName + ", role=" + currentRole);
    	log.info("list");
        return (Collection<Testdbx>) repos.findAll();
    }
    
    @GetMapping("/list/{pageno}/{pagesize}")
    //@RolesAllowed({"USER","ADMIN"})    
    Collection<Testdbx> dbxList(@PathVariable int pageno, @PathVariable int pagesize) {
    	Pageable paging = PageRequest.of(pageno,pagesize, Sort.by ("idx").descending().and(Sort.by("idx")));
    	Page <Testdbx> pageresult = pagerepos.findAll (paging);
    	List <Testdbx> list = pageresult.toList();
    	return list;
    }    
    
    @GetMapping("/list/{id}")
    //@RolesAllowed({"USER","ADMIN"})    
    ResponseEntity<?> getDbx(@PathVariable Long id) {
    	log.info("get user info");
        Optional<Testdbx> item = repos.findById(id);
        return item.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/list")
    //@RolesAllowed({"USER","ADMIN"})    
    ResponseEntity<Testdbx> createDbx(@Valid @RequestBody Testdbx dbx) throws URISyntaxException {
        log.info("create:", dbx);
        simpMessagingTemplate.convertAndSend("/topic/user", "CREATE");		    	
        Testdbx result = repos.save(dbx);
        return ResponseEntity.created(new URI("/data/list/" + result.getIdx())).body(result);
    }

    @PutMapping("/list/{id}")
    //@RolesAllowed({"USER","ADMIN"})        
    ResponseEntity<Testdbx> updateDbx(@Valid @RequestBody Testdbx dbx) {
        log.info("update:", dbx);
        Testdbx result = repos.save(dbx);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/list/{id}")
    //@RolesAllowed({"USER","ADMIN"})    
    public ResponseEntity<?> deleteDbx(@PathVariable Long id) {
        log.info("delete:", id);
        repos.deleteById(id);
        return ResponseEntity.ok().build();
    }    
}
