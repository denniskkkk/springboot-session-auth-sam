package dbx.zzzz.model;

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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbldbx")
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Testdbx {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;
	private String name;
	private String address;
	
	public Testdbx(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("TestDbx: idx=%d, name=%s, address=%s", this.idx, this.name, this.address);
	}

}
