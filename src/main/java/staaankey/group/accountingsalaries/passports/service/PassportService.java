package staaankey.group.accountingsalaries.passports.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.passports.exception.PassportNotCreatedException;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;
import staaankey.group.accountingsalaries.passports.model.Passport;
import staaankey.group.accountingsalaries.passports.repository.PassportRepository;
import staaankey.group.accountingsalaries.passports.web.dto.PassportDto;

@Service
public class PassportService {
    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }


    public Integer save(PassportDto passportDto) throws PassportNotCreatedException {
        Passport passport = new Passport();
        BeanUtils.copyProperties(passportDto, passport);
        try {
            return passportRepository.save(passport);
        } catch (PassportNotCreatedException e) {
            e.printStackTrace();
            throw new PassportNotCreatedException("Can't save passport!");
        }
    }

    public Integer delete(int passportId) throws PassportNotFoundException {
        if (passportRepository.delete(passportId) == 0) {
            throw new PassportNotFoundException("Passport not found!");
        } else {
            return passportRepository.delete(passportId);
        }
    }

    public Passport findByPassportId(int passportId) throws PassportNotFoundException {
        return passportRepository.findByPassportId(passportId);
    }
}
