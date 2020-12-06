package dbx.zzzz.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import dbx.zzzz.model.Role;
import dbx.zzzz.model.Testdbx;
import dbx.zzzz.model.User;
import dbx.zzzz.repository.RoleRepository;
import dbx.zzzz.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/data")
@CrossOrigin(origins = { "http://localhost", "http://localhost:3000" }, maxAge=3600)
public class UserRstController {
	@Autowired
	private UserRepository userrepos;
	@Autowired
	private RoleRepository rolerepos;
	
    @GetMapping("/userslist")
    public Collection<User> userList() {
    	log.info("list users");
        return (Collection<User>) userrepos.findAll();
    }
    
    @GetMapping("/roleslist")
    public Collection<Role> roleList() {
    	return (Collection<Role>) rolerepos.findAll();
    }
    
    @GetMapping("/userslist/{id}")
    public ResponseEntity<?> userInfo(@PathVariable Long id) {
    	log.info("user by id: " + id);
        Optional <User> items = userrepos.findById(id);    	
        return items.map( response -> ResponseEntity.ok().body(response))
        .orElse ( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/userslist")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("user create:");
        User result = userrepos.save(user);
        return ResponseEntity.created(new URI("/data/userslist/" + result.getId())).body(result);
    }

    @PutMapping("/userslist/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("user update:", user);
        User result = userrepos.save(user);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/userslist/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("user delete:", id);
        userrepos.deleteById(id);
        return ResponseEntity.ok().build();
    }     
    
    
}
