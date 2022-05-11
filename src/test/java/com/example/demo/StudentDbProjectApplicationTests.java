package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controller.StudentController;
import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(JUnitPlatform.class)
@WebMvcTest(StudentController.class)
class StudentDbProjectApplicationTests {
	@Autowired
	private MockMvc mvc;



	@MockBean
	StudentDao repo;

	
	public static String asJsonString(final Object obj) throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(obj);
	}
	@Test
	void Create() throws Exception {
		Student s=new Student();
		s.setSid(1);
		s.setSname("Karthik");
		s.setSbranch("ECE");
		s.setScgpa("8");
		//when(repo.save(s)).thenReturn(s);
		URI uri = new URI("/addStudents");
		mvc.perform(MockMvcRequestBuilders.post(uri)
		.content(asJsonString(s))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
    public void getAllItems() throws Exception {
        List<Student> list = Arrays.asList(new Student(1,"Karthik","ECE","9"));
        repo.findAll().forEach(list::add);
        Mockito.when(list).thenReturn(list);
        mvc.perform(MockMvcRequestBuilders.get("/getStudents"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
	@Test
    public void getItem() throws Exception{
        Student s = new Student(1,"Karthik","ECE","9.6");
        repo.save(s);
    
       System.out.println(s);
        Optional<Student> s2=repo.findById(1);
        System.out.println(s2);
    
        Mockito.when(s2).thenReturn(s2);
        mvc.perform(MockMvcRequestBuilders.get("/getStudents/1")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$s2.getId()", is(1)));
                //.andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("karthik")))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.category",Matchers.is("ECE")));
        Mockito.verify(repo).findById(1);
    }
	@Test
	void Update() throws Exception {
		List<Student> list = Arrays.asList(new Student(1,"Karthik","ECE","9"));
        repo.findAll().forEach(list::add);
        Mockito.when(list).thenReturn(list);
        System.out.println(list);
		URI uri = new URI("/updateStudents/1");
		for(int i=0; i<list.size();i++) {
			
			Student s1=list.get(i);
			if(s1.getSid()==1) {
				s1.setSname("Kar");
				mvc.perform(MockMvcRequestBuilders.put(uri)
						.content(asJsonString(s1))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
			}
		}
	}
	@Test
    public void deleteItem() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/deleteStudents/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
