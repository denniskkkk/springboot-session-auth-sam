package dbx.zzzz;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import dbx.zzzz.model.Testdbx;
import dbx.zzzz.repository.TestdbxRepository;


@EnableScheduling
@SpringBootApplication
public class DatadbxApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DatadbxApplication.class);
	
	@Autowired
	private TestdbxRepository repos;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DatadbxApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("StartApplication...");
		repos.deleteAll();
		Stream.of ("one", "two", "three", "four").forEach (name -> repos.save (new Testdbx (name, "address")));
		for (int z = 0; z < 100; z++) {
			repos.save(new Testdbx("apple_" + z, "left_on_corner_" + z));
		}
		repos.save(new Testdbx("appleii", "left corner"));
		//Testdbx e = Testdbx.builder().name ("out").address ("in").build();
		repos.findAll().forEach(x -> System.out.println(x));		
		repos.findAllByName ("apple").forEach(x -> System.out.println(x));	
		repos.findByName("appleii").forEach(x-> System.out.println (x));
		repos.findByAddress ("left corner").forEach(x-> System.out.println (x));
	//	repos.findByAddress("apple").ifPresent (x-> System.out.println (x));		
	//    repos.findById(1000L).ifPresent (x->System.out.println (x));
	    repos.findByIdx (1000L).forEach (x->System.out.println(x));
	}

}

