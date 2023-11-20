package staaankey.group.accountingsalaries.titiles.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotFoundException;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotSavedException;
import staaankey.group.accountingsalaries.titiles.service.TitleService;
import staaankey.group.accountingsalaries.titiles.web.dto.TitleDto;

@RestController
@RequestMapping("/titles")
public class TitleController {
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<?> saveTitle(@RequestBody TitleDto titleDto) {
        try {
            return new ResponseEntity<>(titleService.save(titleDto), HttpStatus.CREATED);
        } catch (TitleNotSavedException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.NOT_FOUND.value(), "Title " + titleDto.toString() + "not created!"), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTitle(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(titleService.delete(id), HttpStatus.OK);
        } catch (TitleNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Title with id: %d not found".formatted(id)
                    ), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTitle(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(titleService.findById(id), HttpStatus.OK);
        } catch (TitleNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Title with id: %d not found".formatted(id)
                    ), HttpStatus.NOT_FOUND);
        }
    }

}
