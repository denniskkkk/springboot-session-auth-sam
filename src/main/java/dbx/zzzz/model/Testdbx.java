package dbx.zzzz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tbldbx")
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testdbx {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;

	private String name;
	private String address;
	
	public Testdbx () {
		
	}

	public Testdbx(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("TestDbx: idx=%d, name=%s, address=%s", this.idx, this.name, this.address);
	}

}
