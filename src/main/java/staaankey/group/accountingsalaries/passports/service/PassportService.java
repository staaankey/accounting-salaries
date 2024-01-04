package staaankey.group.accountingsalaries.passports.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.passports.exception.PassportNotCreatedException;
import staaankey.group.accountingsalaries.passports.exception.PassportNotFoundException;
import staaankey.group.accountingsalaries.passports.model.Passport;
import staaankey.group.accountingsalaries.passports.repository.PassportRepository;
import staaankey.group.accountingsalaries.passports.web.dto.PassportDto;

import jakarta.transaction.Transactional;

@Service
public class PassportService {
    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }


    public Passport save(PassportDto passportDto) throws PassportNotCreatedException {
        Passport passport = new Passport();
        BeanUtils.copyProperties(passportDto, passport);
        return passportRepository.save(passport);
    }

    @Transactional
    public Boolean deletePassportBySeries(String series) throws PassportNotFoundException {
        return passportRepository.deletePassportBySeries(series);
    }

    public Passport findPassportBySeries(String series) {
        return passportRepository.findPassportBySeries(series);
    }
}
