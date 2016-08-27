package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import models.Student;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller {

	public static Result allStudents() {
		List<Student> students = Student.finder.all();
		
		// To get one student pass the id of the student record.
		// Student oneStudent = Student.finder.byId("67"); // pass the student id here.
		// System.out.println(oneStudent.getName());

		/*
		If there is not data to respond then return ok() or do following.
		ObjectNode result = Json.newObject();
		String name = json.findPath("name").getTextValue();
		if(name == null) {
		   result.put("status", "KO");
		   result.put("message", "Missing parameter [name]");
		   return badRequest(result);
		} else {
		   result.put("status", "OK");
		   result.put("message", "Hello " + name);
		   return ok(result);
		}
		*/

		System.out.println("Row count = "+ students.size());

		// If no data then return empty result.
		if(null == students || students.size() == 0) {
			return ok();
		}
		
		// convert all the student objects and return them as a Json to the UI.
		return ok(Json.toJson(students));
	}
	
	public static Result addStudent() {
//		System.out.println("Add Student Controller");
		JsonNode json = request().body().asJson();

		/*Student s = new Student();
		s.name = json.get("name").asText();
		s.email = json.get("email").asText();
		s.age = Integer.parseInt(json.get("age").asText());*/
		
		Student s = Json.fromJson(json, Student.class);
		
		// save - saves the record and creates a new id.
		s.save();
		
		// convert the student object to Json and return.
		return ok(Json.toJson(s));
	}
	
	public static Result deleteStudent() {
//		System.out.println("Delete Student Method");
		
		JsonNode json = request().body().asJson();
		Student s = Json.fromJson(json, Student.class);
		
		// delete - will delete the record using the student id.
		s.delete();
		
		// convert the deleted student object to Json and return.
		return ok(Json.toJson(s));
	}
	
	public static Result updateStudent() {
//		System.out.println("Update Student Method");
		JsonNode json = request().body().asJson();
		Student s = Json.fromJson(json, Student.class);
		
		// update - Updates the student object using the student id.
		s.update();
		
		// Update student object and return it.
		return ok(Json.toJson(s));
	}
}
