package staaankey.group.accountingsalaries.titles.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.titles.exception.TitleNotFoundException;
import staaankey.group.accountingsalaries.titles.exception.TitleNotSavedException;
import staaankey.group.accountingsalaries.titles.service.TitleService;
import staaankey.group.accountingsalaries.titles.web.dto.TitleDto;

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


    @DeleteMapping("/{serial}")
    public ResponseEntity<?> deleteTitle(@PathVariable String serial) {
        return new ResponseEntity<>(titleService.deleteTitleBySerial(serial), HttpStatus.OK);
    }

    @GetMapping("/{serial}")
    public ResponseEntity<?> findTitle(@PathVariable String serial) {
        return new ResponseEntity<>(titleService.findTitleBySerial(serial), HttpStatus.OK);
    }

}
