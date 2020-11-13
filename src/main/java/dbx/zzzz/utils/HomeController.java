package dbx.zzzz.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dbx.zzzz.services.IndexService;
import dbx.zzzz.services.StartComService;

@Controller
public class HomeController {
		private final Logger logger = LoggerFactory.getLogger ("HomeController.class");
		@Autowired
		private final IndexService service;
		
		@Autowired
		private final StartComService sc;
		
		public HomeController () {
			//this.service = service;
			service = null;
			sc = null;
		}
		
		@RequestMapping ("/home")
		public @ResponseBody String index() {
			logger.info (sc.getData());
			return service.index();
		}
}
