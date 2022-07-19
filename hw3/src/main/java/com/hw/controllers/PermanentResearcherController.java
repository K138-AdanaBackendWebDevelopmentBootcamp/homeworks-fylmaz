package com.hw.controllers;

import com.hw.models.PermanentResearcher;
import com.hw.services.PermanentResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permanent")
public class PermanentResearcherController {

    @Autowired
    private PermanentResearcherService permanentResearcherService;

    @GetMapping("/all")
    public List<PermanentResearcher> findAllInstructors(){
        return permanentResearcherService.findAll();
    }

    @GetMapping("/{id}")
    public PermanentResearcher findInstructorById(@PathVariable int id) {
        return permanentResearcherService.findById(id);
    }

    @PostMapping("/save")
    public void saveInstructor(@RequestBody PermanentResearcher instructor) {
        permanentResearcherService.saveToDatabase(instructor);
    }

    @DeleteMapping("/deleteInstructor")
    public void deleteInstructor(@RequestBody PermanentResearcher instructor) {
        permanentResearcherService.deleteFromDatabase(instructor);
    }

    @DeleteMapping("/deleteInstructorById/{id}")
    public void deleteInstructor(@PathVariable int id) {
        permanentResearcherService.deleteFromDatabase(id);
    }

    @PutMapping("/updateInstructor/{id}")
    public ResponseEntity<PermanentResearcher> updateInstructor(@RequestBody PermanentResearcher instructor, @PathVariable int id) {
        permanentResearcherService.updateOnDatabase(instructor, id);
        //Return 200 response code if successful
        return ResponseEntity.status(HttpStatus.OK).body(instructor);
    }

}
