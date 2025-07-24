package net.dsa.web3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dsa.web3.dto.StudentDTO;
import net.dsa.web3.entity.StudentEntity;
import net.dsa.web3.repository.StudentRepository;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class StudentService {
		
	private final StudentRepository sr;
	
		public List<StudentDTO> selectAll() {
		
			try {
				List<StudentDTO> students = new ArrayList();
				
				List<StudentEntity> entities = sr.findAll();
				
				for(StudentEntity entity : entities) {
					StudentDTO student = new StudentDTO();
					student.Entity_to_Dto(entity, student);
					students.add(student);					
				}
				
				return students;
				
			}
			catch(Exception e) {
				throw e;
			}
		
	}

		public void save(StudentDTO student) {
			try {
				StudentEntity studententity = new StudentEntity();
				student.Dto_to_Entity(studententity, student);
				sr.save(studententity);
			}
			catch(Exception e) {
				throw e;
			}
			
		}

		public StudentDTO getById(int id) {
			try {
			StudentEntity studetnEntity = sr.findById(id).orElseThrow(()-> new EntityNotFoundException("user not found"));
			StudentDTO student = new StudentDTO();
			student.Entity_to_Dto(studetnEntity, student);
			return student;
			}
			catch(Exception e) {
				throw e;
			}
		}

		public void delete(int id) {
			try {
				boolean isStudentExist = sr.existsById(id);
				if(!isStudentExist) { throw new EntityNotFoundException("user not found");}
				sr.deleteById(id);
			}
			catch(Exception e) {
				throw e;
			}
			
		}

		public void updateStudent(StudentDTO student) {
			try {
				log.debug("[service updateStudent] studetn dto :{}",student);
				StudentEntity studentEntity = new StudentEntity();
				student.Dto_to_Entity(studentEntity, student);
				log.debug("[service updateStudent] studetn entity :{}",studentEntity);
				sr.save(studentEntity);
			}
			catch(Exception e) {
				throw e;
			}
			
		}

}
