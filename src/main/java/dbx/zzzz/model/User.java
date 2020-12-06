package dbx.zzzz.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	    @Id
	    @Column (name = "userid")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column
	    private String username;
	    @Column
	    private String password;
	    @Column
	    private boolean enabled;
	    // constructor 
	    public User (String username, String password, Boolean enabled) {
	    	this.username = username;
	    	this.password = password;
	    	this.enabled = enabled;
	    }
	    
	    
	    /*
	    public User(String username, Role... roles) {
	        this.username = username;
	        this.roles = Stream.of(roles).collect(Collectors.toSet());
	        this.roles.forEach(x -> x.getUsers().add(this));
	    }
	    */
	    
	    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    //@ManyToMany(fetch=FetchType.EAGER,cascade={ CascadeType.REMOVE , CascadeType.REFRESH})	    // remove user will also remove role due to cascade relation REMOVE
	    @ManyToMany(fetch=FetchType.EAGER,cascade={ CascadeType.REFRESH})	    
	    @JoinTable(
	            name = "users_roles",            
	            joinColumns = @JoinColumn(name = "userid",referencedColumnName = "userid"),
	            inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid")
	            )
	    private Set<Role> roles = new HashSet<>();
	   
	    
}
