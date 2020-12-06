package dbx.zzzz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name= "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role {
@Id
@Column (name="roleid")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column
private String name;

//@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="roles")
//private Set<User> users = new HashSet<>();

public Role (String name) {
	this.name = name;
}

}
