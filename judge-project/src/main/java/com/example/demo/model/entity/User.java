package com.example.demo.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private String username;
	private String password;
	private String email;
	private String git;
	private Role role;
	private Set<Homework> homework;

	public User() {

	}
	
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	public Set<Homework> getHomework() {
		return homework;
	}


	public void setHomework(Set<Homework> homework) {
		this.homework = homework;
	}



	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "git")
	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}
	
	@ManyToOne
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
