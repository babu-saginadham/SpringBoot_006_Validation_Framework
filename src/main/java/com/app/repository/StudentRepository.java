package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.jdbc.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("Select student from Student student") //Select * from Student;
	List<Student> findAll1();
	
	@Query("Select student from Student student where student.sname like ?1") //Select * from Student;
	List<Student> findAllBasedOntheName(String name);
	
	/*
	@Query("Select student from Student student where student.sname like ?1 and student.address = ?2") //Select * from Student;
	List<Student> findAllBasedOntheNameAndAddress(String name, String address);
	
	@Query("Select student from Student student where student.sname like :name and student.address = :address") //Select * from Student;
	List<Student> findAllBasedOntheNameAndAddress2(@Param("name") String name, @Param("address") String address);
	*/
	
	@Query("Select student from Student student")
	Page<Student> getPaginatedStudents(Pageable pageable);
	
	@Query("Select student from Student student where student like ?1")
	Page<Student> getPaginatedStudents1(String nameStart, Pageable pageable);
	
	@Modifying
	@Query("Delete from Student student where student.sname = ?1")
	int deleteStudentBasedOnName(String name);
	
}
