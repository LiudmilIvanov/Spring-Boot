package softuni.workshop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority{

	private String authority;
	
	public Role() {

	}

	public Role(String authority) {
		this.authority = authority;
	}
	
	@Override
	@Column(name = "authority", unique = true, nullable = false)
	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
