package dbx.zzzz;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dbx.zzzz.controller.CronJob;
import dbx.zzzz.controller.CronJobMin;
import dbx.zzzz.model.Role;
import dbx.zzzz.model.Testdbx;
import dbx.zzzz.model.User;
import dbx.zzzz.repository.RoleRepository;
import dbx.zzzz.repository.TestdbxRepository;
import dbx.zzzz.repository.UserRepository;


@SpringBootApplication
//@EnableAuthorizationServer
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class DatadbxApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DatadbxApplication.class);
	
	@Autowired
	private TestdbxRepository repos;
	@Autowired 
	private UserRepository userrepos;
	@Autowired 
	private RoleRepository rolerepos;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DatadbxApplication.class, args);

	}
	
	private void generateBcryptPassword () {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String password [] = {"password", "password1", "password2"};
	    for(int i = 0; i < password.length; i++) {
	        System.out.println(passwordEncoder.encode(password[i]));
	    }
	}

	private void createTestData () {
		repos.deleteAll();
		Stream.of ("one", "two", "three", "four").forEach (name -> repos.save (new Testdbx (name, "address")));
		for (int z = 0; z < 20; z++) {
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
	    //		
	}
	
	private void createTestUser() {
		userrepos.deleteAll();
		rolerepos.deleteAll();
		log.info("OLD USER ROLE REMOVED!!!");
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    try {
	    Stream.of("ADMIN", "USER", "GUEST").forEach( role -> 
	    rolerepos.save(new Role (role))
	    );
		Role r1 = rolerepos.getRoleByName("ADMIN");
		Role r2 = rolerepos.getRoleByName("USER");
		Role r3 = rolerepos.getRoleByName("GUEST");
		User u1 = new User ("admin",passwordEncoder.encode("password"), true);
		User u2 = new User ("user",passwordEncoder.encode("password"), true);
		User u3 = new User ("guest",passwordEncoder.encode("password"), true);
		u1.getRoles().add(r1);
		u2.getRoles().add(r2);
		u3.getRoles().add(r3);
		userrepos.save(u1);
		userrepos.save(u2);
		userrepos.save(u3);
		// test remove guest wont remove ROLE
		User d = userrepos.getUserByUsername("guest");
		if (d != null ) log.info("DELETE TEST USER FOUND");
		userrepos.delete(d);
	    log.info("NEW TEST USER ROLES CREATED!!!");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		createTestData();
		createTestUser();
	    JobKey jobkey1 = new JobKey("job1", "group1");
    	JobDetail job1 = JobBuilder.newJob(CronJob.class).withIdentity(jobkey1).build();
    	Trigger trigger1 = TriggerBuilder .newTrigger().withIdentity("trigger1", "group1")
        .withSchedule( CronScheduleBuilder.cronSchedule("/10 * * * * ?")).build();
    	//
	    JobKey jobkey2 = new JobKey("job2", "group1");
    	JobDetail job2 = JobBuilder.newJob(CronJobMin.class).withIdentity(jobkey2).build();
    	Trigger trigger2 = TriggerBuilder .newTrigger().withIdentity("trigger2", "group1")
        .withSchedule( CronScheduleBuilder.cronSchedule("0 /1 * * * ?")).build();
    	 //
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job1, trigger1);
    	scheduler.scheduleJob(job2, trigger2);
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
            }
        };
    }

}

