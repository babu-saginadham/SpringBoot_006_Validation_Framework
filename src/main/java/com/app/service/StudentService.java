package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.jdbc.model.Student;
import com.app.model.StudentModel;

public interface StudentService {

	public Long createStudent(StudentModel studentModel);
	
	public void updateStudent(StudentModel studentModel);
	
	public void deleteStudent(Long sno);
	
	public List<StudentModel> getAllStudents();
	
	public Page<Student> getAllStudents(String nameStartsWith, 
			Integer pageNo, Integer limit,
			String sortBy, String sortOrder);
	
	public StudentModel getStudent(Long sno);
	
	
}
