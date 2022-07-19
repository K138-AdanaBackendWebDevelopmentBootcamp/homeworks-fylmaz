package com.hw.services;

import com.hw.models.PermanentResearcher;
import com.hw.repositories.PermanentResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermanentResearcherService implements IInstructorService<PermanentResearcher>  {

    @Autowired
    private PermanentResearcherRepository permanentResearcherRepository;
    @Override
    public List<PermanentResearcher> findAll() {
        return permanentResearcherRepository.findAll();
    }

    @Override
    public PermanentResearcher findById(int id) {
        return permanentResearcherRepository.findById(id).get();
    }

    @Override
    public void saveToDatabase(PermanentResearcher instructor) { permanentResearcherRepository.save(instructor); }

    @Override
    public void deleteFromDatabase(PermanentResearcher instructor) { permanentResearcherRepository.delete(instructor); }

    @Override
    public void deleteFromDatabase(int id) {
        permanentResearcherRepository.deleteById(id);
    }

    @Override
    public void updateOnDatabase(PermanentResearcher instructor, int id) {
        PermanentResearcher existingInstructor = permanentResearcherRepository.findById(id).get();

        if(existingInstructor != null){
            existingInstructor.setAddress(instructor.getAddress());
            existingInstructor.setPhoneNumber(instructor.getPhoneNumber());
            existingInstructor.setCourses(instructor.getCourses());
            existingInstructor.setName(instructor.getName());

            permanentResearcherRepository.save(existingInstructor);
        }
    }

}
