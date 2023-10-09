package staaankey.group.accountingsalaries.security.educations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.security.educations.exceptions.EducationNotFindEducation;
import staaankey.group.accountingsalaries.security.educations.exceptions.EducationNotSavedException;
import staaankey.group.accountingsalaries.security.educations.model.Education;
import staaankey.group.accountingsalaries.security.educations.repository.EducationRepository;
import staaankey.group.accountingsalaries.security.educations.web.dto.EducationDto;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {

        this.educationRepository = educationRepository;
    }

    public int save(EducationDto dto) throws EducationNotSavedException {
        Education education = new Education();
        BeanUtils.copyProperties(dto, education);
        try {
            return educationRepository.save(education);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EducationNotSavedException("Education not saved!");
        }

    }

    public Education findEducationById(int id) throws EducationNotFindEducation {
        return educationRepository.findEducationById(id);
    }

    public int deleteEducationById(int id) throws EducationNotFindEducation {
        if (educationRepository.delete(id) == 0) {
            throw new EducationNotFindEducation("Education not found!");
        } else {
            return educationRepository.delete(id);
        }
    }
}
