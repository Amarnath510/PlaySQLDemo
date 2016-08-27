package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Student extends Model {

	public static Finder<String, Student> finder = new Finder<String, Student>(String.class, Student.class);

	private static final long serialVersionUID = 1L;
	
	@Id
	public String id;
	public String name;
	public String email;
	public int age;
	
	public Student() {
		// Default constructor.
	}
	
	public Student(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
