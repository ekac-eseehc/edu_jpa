package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(name = "USERNAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	Team team;
	
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
