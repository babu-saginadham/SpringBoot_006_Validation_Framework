package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.exception.handler.ValidationFailureException;
import com.app.jdbc.model.Student;
import com.app.model.StudentModel;
import com.app.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Long createStudent(StudentModel studentModel) {
		
		if(studentModel.getSname().length() <5) {
			throw new ValidationFailureException("Sname Invalid");
		}
		
		Student studentToSave = new Student();
		studentToSave.setSname(studentModel.getSname());
		
		Student savedData = studentRepository.save(studentToSave);
		
		return savedData.getSno();
	}

	@Override
	public void updateStudent(StudentModel studentModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(Long sno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StudentModel> getAllStudents() {
		List<Student> studentsFromDB = studentRepository.findAll();
	
		List<StudentModel> studentModels = new ArrayList<>();
		for(Student stud:studentsFromDB) {
			StudentModel studentModel = new StudentModel();
			studentModel.setSno(stud.getSno());
			studentModel.setSname(stud.getSname());
			
			studentModels.add(studentModel);
			
		}
		
		//TODO: BeanMapper
		
		return studentModels;
	}

	@Override
	public StudentModel getStudent(Long sno) {
		Optional<Student> studDB = studentRepository.findById(sno);
		
		if(studDB.isPresent()) {
			Student fromDB = studDB.get();
			
			StudentModel stud = new StudentModel();
			stud.setSname(fromDB.getSname());
			stud.setSno(fromDB.getSno());
			return stud;
			
		}else {
			//Throw exception 
			return new StudentModel();
		}
		
		
	}
	
	@Override
	public Page<Student> getAllStudents(String nameStartsWith, 
			Integer pageNo, Integer limit,
			String sortBy, String sortOrder) {
		
		//Way1 - without sorting
		//Pageable pageable = PageRequest.of(pageNo, limit);
		
		
		Sort sort = Sort.by(sortBy);
		if(sortOrder.equals("asc")) {
			sort = sort.ascending();
		}else {
			sort = sort.descending();
		}
		
		Pageable pageable = PageRequest.of(pageNo, limit, sort);
		
		Page<Student> dbData = studentRepository.getPaginatedStudents(pageable);
		
		
		return dbData;
	}

}
