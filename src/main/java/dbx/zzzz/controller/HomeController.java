package dbx.zzzz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dbx.zzzz.services.IndexService;

@Controller
public class HomeController {
		private final Logger logger = LoggerFactory.getLogger ("HomeController.class");
		@Autowired
		private final IndexService service;
				
		public HomeController () {
			//this.service = service;
			service = new IndexService();
		}
		/*
		@RequestMapping ("/home")
		public @ResponseBody String index() {
			return service.index();
		}
		*/
		/*
		@RequestMapping ("/admin")
	    @RolesAllowed({"ADMIN"})		
		public @ResponseBody String admin() {
			return service.index();
		}
		*/		
		/*@RequestMapping ("/user")
	    @RolesAllowed({"USER","ADMIN"})		
		public @ResponseBody String user() {
			return service.index();
		}*/		
		// return template
		@GetMapping("/menu")
		public String menu() {
			return "menu";
		}
		
		@GetMapping("/user")
		public String user() {
			return "user";
		}
		
		@GetMapping("/admin")
		public String admin() {
			return "admin";
		}
		
}
