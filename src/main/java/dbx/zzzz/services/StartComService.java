package dbx.zzzz.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StartComService {
    private final Logger log = LoggerFactory.getLogger(StartComService.class);

	
	public String getData () {
		return "special data";
	}
}
