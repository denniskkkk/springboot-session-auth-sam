package dbx.zzzz.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbx.zzzz.model.Group;

@RestController
public class RestServerGroup {
	// getmapping only map rest get request
	@GetMapping ("/group")
	public Group group (@RequestParam (value="name", defaultValue="first") String name) {
		Group g = new Group (1, "name", "address");
		return g;
	}
}
