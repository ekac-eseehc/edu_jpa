package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "USERNAME")
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	Team team;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;
	
	@ManyToMany
	@JoinTable(name = "member")
	private List<MemberProduct> memberProduct = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
