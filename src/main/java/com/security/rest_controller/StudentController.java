package com.security.rest_controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.entity.Student;

@RestController
public class StudentController 
{
	List<Student> listStd=new ArrayList<>(List.of(
			new Student(101,"Sagar",90.0),new Student(102,"Ram",95.0),new Student(103,"Sita",85.0)
			));
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		return listStd;
	}
	
	
	@PostMapping("/students")
	public void addStudent(@RequestBody Student std)
	{
		listStd.add(std);
	}
}
