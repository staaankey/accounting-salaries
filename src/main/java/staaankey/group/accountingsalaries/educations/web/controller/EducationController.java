package staaankey.group.accountingsalaries.educations.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotFindEducation;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotSavedException;
import staaankey.group.accountingsalaries.educations.service.EducationService;
import staaankey.group.accountingsalaries.educations.web.dto.EducationDto;

@RestController
@RequestMapping("/educations")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }


    @PostMapping
    public ResponseEntity<?> saveEducation(@RequestBody EducationDto dto) {
        try {
            return new ResponseEntity<>(educationService.save(dto), HttpStatus.CREATED);
        } catch (EducationNotSavedException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), String.format("Education with series %s and diploma number %s not saved!", dto.getDiplomaSeries(), dto.getDegree())), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEducationById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(educationService.findEducationById(id), HttpStatus.OK);
        } catch (EducationNotFindEducation e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Education with id %d not found".formatted(id)
                    ), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEducationById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(educationService.deleteEducationById(id), HttpStatus.OK);
        } catch (EducationNotFindEducation e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Education with id %d not found".formatted(id)
                    ), HttpStatus.NOT_FOUND);
        }
    }
}
