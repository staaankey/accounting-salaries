package staaankey.group.accountingsalaries.passports.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import staaankey.group.accountingsalaries.departments.exception.AppError;
import staaankey.group.accountingsalaries.passports.exception.PassportNotCreatedException;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;
import staaankey.group.accountingsalaries.passports.service.PassportService;
import staaankey.group.accountingsalaries.passports.web.dto.PassportDto;

@RestController("/passports")
public class PassportController {
    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }


    @PostMapping("/savePassport")
    public ResponseEntity<?> savePassport(@RequestBody PassportDto passportDto) {
        try {
            return new ResponseEntity<>(passportService.save(passportDto), HttpStatus.CREATED);
        } catch (PassportNotCreatedException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Passport " + passportDto.toString() + "not created!"), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deletePassport/{passportId}")
    public ResponseEntity<?> deletePassport(@PathVariable Long passportId) {
        try {
            return new ResponseEntity<>(passportService.delete(passportId), HttpStatus.OK);
        } catch (PassportNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Passport with %d not presented in database!".formatted(passportId)), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{passportId}")
    public ResponseEntity<?> findByPassportId(@PathVariable Long passportId) {
        try {
            return new ResponseEntity<>(passportService.findByPassportId(passportId), HttpStatus.OK);
        } catch (PassportNotFoundException e) {
            return new ResponseEntity<>(
                    new AppError(
                            HttpStatus.NOT_FOUND.value(), "Passport with id %d not found".formatted(passportId)
                    ), HttpStatus.NOT_FOUND);
        }
    }
}
