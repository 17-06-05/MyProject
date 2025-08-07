package com.example.Students.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Students.Entity.Address;
import com.example.Students.Entity.Qualification;
import com.example.Students.Entity.Student;
import com.example.Students.Repository.AddressRepository;
import com.example.Students.Repository.QualificationRepository;
import com.example.Students.Repository.StudentRepository;
import com.example.Students.dto.StudentDTO;
import com.example.Students.dto.StudentFullDTO;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private QualificationRepository qualificationRepo;
    
    public List<StudentFullDTO> getAllStudentWithDetails() {
        List<Student> students = repo.findAll();
        List<StudentFullDTO> fullDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentFullDTO dto = new StudentFullDTO();
            dto.setsNo(student.getsNo());
            dto.setStudentId(student.getStudentId());
            dto.setStudentName(student.getStudentName());
            dto.setDob(student.getDob());
            dto.setAge(student.getAge());
            dto.setGender(student.getGender());
            dto.setPhone(student.getPhone());
            dto.setMail(student.getMail());

            Address address = addressRepo.findByStudentId(student.getStudentId());
            if (address != null) {
                dto.setDoorNo(address.getDoorNo());
                dto.setStreetName(address.getStreetName());
                dto.setCity(address.getCity());
                dto.setState(address.getState());
                dto.setCountry(address.getCountry());
                dto.setPincode(address.getPincode());
            }

            Qualification qualification = qualificationRepo.findByStudentId(student.getStudentId());
            if (qualification != null) {
                dto.setCourseId(qualification.getCourseId());
                dto.setCourseName(qualification.getCourseName());
                dto.setUniversityName(qualification.getUniversityName());
                dto.setCgpa(qualification.getCgpa());
                dto.setYop(qualification.getYop());
            }

            fullDTOs.add(dto);
        }

        return fullDTOs;
    }


    public StudentFullDTO getStudentWithAllDetails(int id) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isEmpty()) return null;

        Student student = optionalStudent.get();
        StudentFullDTO dto = new StudentFullDTO();

        dto.setStudentId(student.getStudentId());
        dto.setStudentName(student.getStudentName());
        dto.setDob(student.getDob());
        dto.setAge(student.getAge());
        dto.setGender(student.getGender());
        dto.setPhone(student.getPhone());
        dto.setMail(student.getMail());

        Address address = addressRepo.findByStudentId(id);
        if (address != null) {
            dto.setDoorNo(address.getDoorNo());
            dto.setStreetName(address.getStreetName());
            dto.setCity(address.getCity());
            dto.setState(address.getState());
            dto.setCountry(address.getCountry());
            dto.setPincode(address.getPincode());
        }

        Qualification qualification = qualificationRepo.findByStudentId(id);
        if (qualification != null) {
            dto.setCourseId(qualification.getCourseId());
            dto.setCourseName(qualification.getCourseName());
            dto.setUniversityName(qualification.getUniversityName());
            dto.setCgpa(qualification.getCgpa());
            dto.setYop(qualification.getYop());
        }

        return dto;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Optional<Student> getByStudentId(int id) {
        return repo.findById(id);
    }

    public Student addStudent(StudentDTO dto) {
        
        Student student = new Student();
        student.setsNo(dto.getsNo());
        student.setStudentId(dto.getStudentId());
        student.setStudentName(dto.getStudentName());
        student.setDob(dto.getDob());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        student.setMail(dto.getMail());

        
        Student savedStudent = repo.save(student);

        
        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setStudentId(savedStudent.getStudentId());
            address.setDoorNo(dto.getAddress().getDoorNo());
            address.setStreetName(dto.getAddress().getStreetName());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setCountry(dto.getAddress().getCountry());
            address.setPincode(dto.getAddress().getPincode());
            addressRepo.save(address);
        }

        
        if (dto.getQualification() != null) {
            Qualification qualification = new Qualification();
            qualification.setStudentId(savedStudent.getStudentId());
            qualification.setCourseId(dto.getQualification().getCourseId());
            qualification.setCourseName(dto.getQualification().getCourseName());
            qualification.setUniversityName(dto.getQualification().getUniversityName());
            qualification.setCgpa(dto.getQualification().getCgpa());
            qualification.setYop(dto.getQualification().getYop());
            qualificationRepo.save(qualification);
        }

        return savedStudent;
    }


    public String deleteStudent(int id) {
        if (repo.existsById(id)) {
            addressRepo.deleteById(id);
            qualificationRepo.deleteById(id);
            repo.deleteById(id);
            return "Student with ID " + id + " deleted successfully.";
        } else {
            return "Student with ID " + id + " not found.";
        }
    }

    public Student updateStudent(StudentDTO dto) {
        Optional<Student> optionalStudent = repo.findById(dto.getStudentId());
        if (optionalStudent.isEmpty()) return null;

        Student student = optionalStudent.get();
        student.setStudentName(dto.getStudentName());
        student.setDob(dto.getDob());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());
        student.setMail(dto.getMail());

        Student updated = repo.save(student);

        if (dto.getAddress() != null) {
            Address address = addressRepo.findByStudentId(student.getStudentId());
            if (address == null) address = new Address();
            address.setStudentId(student.getStudentId());
            address.setDoorNo(dto.getAddress().getDoorNo());
            address.setStreetName(dto.getAddress().getStreetName());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setCountry(dto.getAddress().getCountry());
            address.setPincode(dto.getAddress().getPincode());
            addressRepo.save(address);
        }

        if (dto.getQualification() != null) {
        	Qualification qualification = qualificationRepo.findByStudentId(student.getStudentId());
        	if (qualification == null) qualification = new Qualification();
            qualification.setStudentId(student.getStudentId());
            qualification.setCourseId(dto.getQualification().getCourseId());
            qualification.setCourseName(dto.getQualification().getCourseName());
            qualification.setUniversityName(dto.getQualification().getUniversityName());
            qualification.setCgpa(dto.getQualification().getCgpa());
            qualification.setYop(dto.getQualification().getYop());
            qualificationRepo.save(qualification);
        }

        return updated;
    }


	public List<com.example.Students.dto.StudentDTOI> getStudentByQuery(int id) {
		return repo.getStudentByQuery(id);
	}
}
