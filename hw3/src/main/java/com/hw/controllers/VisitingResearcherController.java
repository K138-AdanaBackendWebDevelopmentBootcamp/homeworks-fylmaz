package com.hw.controllers;
import com.hw.models.Instructor;

import com.hw.models.VisitingResearcher;
import com.hw.services.PermanentResearcherService;
import com.hw.services.VisitingResearcherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visiting")
public class VisitingResearcherController {

    @Autowired
    private VisitingResearcherService visitingResearcherService;


    @GetMapping("/all")
    public List<VisitingResearcher> findAllInstructors(){
        return visitingResearcherService.findAll();
    }

    @GetMapping("/{id}")
    public VisitingResearcher findInstructorById(@PathVariable int id) {
        return visitingResearcherService.findById(id);
    }

    @PostMapping("/save")
    public void saveInstructor(@RequestBody VisitingResearcher instructor) {
        visitingResearcherService.saveToDatabase(instructor);
    }

    @DeleteMapping("/deleteInstructor")
    public void deleteInstructor(@RequestBody VisitingResearcher instructor) {
        visitingResearcherService.deleteFromDatabase(instructor);
    }

    @DeleteMapping("/deleteInstructorById/{id}")
    public void deleteInstructor(@PathVariable int id) {
        visitingResearcherService.deleteFromDatabase(id);
    }

    @PutMapping("/updateInstructor/{id}")
    public ResponseEntity<VisitingResearcher> updateInstructor(@RequestBody VisitingResearcher instructor, @PathVariable int id) {
        visitingResearcherService.updateOnDatabase(instructor, id);
        //Return 200 response code if successful
        return ResponseEntity.status(HttpStatus.OK).body(instructor);
    }

}
