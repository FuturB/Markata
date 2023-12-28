package com.futureB.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id", unique = true)
	private String emailId;

	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "year")
	private String year;

	@Column(name = "month")
	private String month;

	@Column(name = "date")
	private String date;

	@Column(name= "Birth date")
	private LocalDate DOB;


	@Column(name= "enabled")
	private boolean enabled = false;

	@OneToMany(mappedBy = "user")
	private List<Token> tokens;

	@ManyToMany(mappedBy = "user")
	private List<Address> address;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
	}

	public User(long id, String firstName, String lastName, String emailId, String password, String year, String month, String date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;

		this.year = year;
		this.month = month;
		this.date = date;
//		System.out.println("This is the date of birth" + LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(date)) + "\n");
//		this.DOB = LocalDate.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(date));
//		System.out.println("This is the date of birth" + this.DOB + "\n");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getYear() {
//		return year;
//	}
//
//	public void setYear(String year) {
//		this.year = year;
//	}
//
//	public String getMonth() {
//		return month;
//	}
//
//	public void setMonth(String month) {
//		this.month = month;
//	}
//
//	public String getDate() {
//		return date;
//	}
//
//	public void setDate(String date) {
//		this.date = date;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setDOB() {
		this.DOB = LocalDate.of(Integer.parseInt(this.year),Integer.parseInt(this.month),Integer.parseInt(this.date));;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", emailId='" + emailId + '\'' +
				", password='" + password + '\'' +
				", DOB = " + DOB + '\'' +
				'}';
	}
}
