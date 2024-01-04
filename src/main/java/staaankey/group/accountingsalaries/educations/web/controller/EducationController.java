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
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    String.format("Education with series %s and diploma number %s not saved!", dto.getDiplomaSeries(), dto.getDegree())), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{number}")
    public ResponseEntity<?> findEducationByDiplomaNumber(@PathVariable Integer number) {
        return new ResponseEntity<>(educationService.findByDiplomaNumber(number), HttpStatus.OK);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<?> deleteEducationByDiplomaNumber(@PathVariable Integer number) {
        educationService.deleteEducationByDiplomaNumber(number);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
