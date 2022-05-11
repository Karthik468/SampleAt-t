package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;

import java.util.*;


@RestController
public class StudentController {
	
	
	@Autowired
	StudentDao repo;
	
	
	@PostMapping("/addStudents")
	public String addStudent(@RequestBody Student s) {
		System.out.println("Kk");
		repo.save(s);
		return "Data_Inserted";
	}
	
	@GetMapping("/getStudents")
	public List<Student> getStudents(){
		List<Student> list=new ArrayList<Student>();
		repo.findAll().forEach(list::add);
		System.out.println(list);
		return list;
	}
	
	@GetMapping("/getStudents/{sid}")
	public Optional<Student> getEachStudent(@PathVariable("sid") int id) {
		return repo.findById(id);
	}
	
	
	@PutMapping("/updateStudents/{sid}")
	public String updateStudents(@RequestBody Student s, @PathVariable("sid") int id) {
		List<Student> list=new ArrayList<Student>();
		repo.findAll().forEach(list::add);
		System.out.println(list);
		for(int i=0; i<list.size();i++) {
			
			Student s1=list.get(i);
			if(s1.getSid()==id) {
				repo.save(s);
				return "Row Updated";
			}
		}
		return "invalid sid";
	}
	
	@DeleteMapping("/deleteStudents/{sid}")
	public String deleteStudent(@PathVariable("sid") int id) {
		repo.deleteById(id);
		return "Row_Deleted";
	}
}
