package dbx.zzzz.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbx.zzzz.model.Group;

@RestController
@CrossOrigin(origins = { "http://localhost", "http://localhost:3000" })
public class RestServerGroup {
	// getmapping only map rest get request
	@GetMapping ("/group")
    @RolesAllowed({"USER","ADMIN"})	
	public Group group (@RequestParam (value="name", defaultValue="first") String name) {
		Group g = new Group (1, "name", "address");
		return g;
	}
}
