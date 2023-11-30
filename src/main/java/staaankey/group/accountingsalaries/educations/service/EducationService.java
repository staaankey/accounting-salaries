package staaankey.group.accountingsalaries.educations.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotFindEducation;
import staaankey.group.accountingsalaries.educations.exceptions.EducationNotSavedException;
import staaankey.group.accountingsalaries.educations.model.Education;
import staaankey.group.accountingsalaries.educations.repository.EducationRepository;
import staaankey.group.accountingsalaries.educations.web.dto.EducationDto;


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

    @Transactional
    public int deleteEducationById(int id) throws EducationNotFindEducation {
        if (educationRepository.delete(id) == 0) {
            throw new EducationNotFindEducation("Education not found!");
        } else {
            return educationRepository.delete(id);
        }
    }
}
