package com.hw.services;

import com.hw.models.VisitingResearcher;

import com.hw.repositories.VisitingResearcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitingResearcherService implements IInstructorService<VisitingResearcher> {


    @Autowired
    private VisitingResearcherRepository visitingResearcherRepository;

    @Override
    public List<VisitingResearcher> findAll() {
        return visitingResearcherRepository.findAll();
    }

    @Override
    public VisitingResearcher findById(int id) {
        return visitingResearcherRepository.findById(id).get();
    }

    @Override
    public void saveToDatabase(VisitingResearcher instructor) { visitingResearcherRepository.save(instructor); }

    @Override
    public void deleteFromDatabase(VisitingResearcher instructor) { visitingResearcherRepository.delete(instructor); }

    @Override
    public void deleteFromDatabase(int id) {
        visitingResearcherRepository.deleteById(id);
    }

    @Override
    public void updateOnDatabase(VisitingResearcher instructor, int id) {
        VisitingResearcher existingInstructor = visitingResearcherRepository.findById(id).get();

        if(existingInstructor != null){
            existingInstructor.setAddress(instructor.getAddress());
            existingInstructor.setPhoneNumber(instructor.getPhoneNumber());
            existingInstructor.setCourses(instructor.getCourses());
            existingInstructor.setName(instructor.getName());

            visitingResearcherRepository.save(existingInstructor);
        }
    }
}
