package com.example.Students.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Students.Entity.Qualification;
import com.example.Students.Repository.QualificationRepository;
import com.example.Students.dto.QualificationDTO;
@Service
public class QualificationService {
	
	@Autowired
    private QualificationRepository repo;
	
	public List<Qualification> getAllQualifDetails() {
        return repo.findAll();
    }
	
	public Optional<Qualification> getQualifById(int id){
		return repo.findById(id);
	}
	
	public Qualification addQualif(QualificationDTO dto) {
		Qualification Qualif = new Qualification();
		
		Qualif.setsNo(dto.getsNo());
		Qualif.setStudentId(dto.getStudentId());
		Qualif.setCourseId(dto.getCourseId());
		Qualif.setCourseName(dto.getCourseName());
		Qualif.setUniversityName(dto.getUniversityName());
		Qualif.setCgpa(dto.getCgpa());
		Qualif.setYop(dto.getYop());
	
    	return repo.save(Qualif);
	}
	
	public Qualification updateQualif(QualificationDTO updateQualif) {
		   Optional<Qualification> existingQuali = repo.findById(updateQualif.getsNo());
		           
		   if(existingQuali.isPresent()){
			existingQuali.get().setStudentId(updateQualif.getStudentId());
			existingQuali.get().setCourseName(updateQualif.getCourseName());
			existingQuali.get().setCourseId(updateQualif.getCourseId());
			existingQuali.get().setCgpa(updateQualif.getCgpa());
			existingQuali.get().setUniversityName(updateQualif.getUniversityName());
			existingQuali.get().setYop(updateQualif.getYop());
		    	    		    
		    return repo.saveAndFlush(existingQuali.get());
		   }
		   return null;
		}
	public String deleteQualif(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Qualification with ID " + id + " deleted successfully.";
        } else {
            return "Qualification with ID " + id + " not found.";
        }
    }
}
