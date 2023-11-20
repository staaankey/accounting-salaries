package staaankey.group.accountingsalaries.passports.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.passports.exception.PassportNotCreatedException;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;
import staaankey.group.accountingsalaries.passports.service.PassportService;
import staaankey.group.accountingsalaries.passports.web.dto.PassportDto;

@RestController
@RequestMapping("/passports")
public class PassportController {
    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }


    @PostMapping
    public ResponseEntity<?> savePassport(@RequestBody PassportDto passportDto) {
        try {
            return new ResponseEntity<>(passportService.save(passportDto), HttpStatus.CREATED);
        } catch (PassportNotCreatedException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Passport " + passportDto.toString() + "not created!"), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassport(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(passportService.delete(id), HttpStatus.OK);
        } catch (PassportNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Passport with %d not presented in database!".formatted(id)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByPassportId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(passportService.findByPassportId(id), HttpStatus.OK);
        } catch (PassportNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Passport with id %d not found".formatted(id)
                    ), HttpStatus.NOT_FOUND);
        }
    }
}
