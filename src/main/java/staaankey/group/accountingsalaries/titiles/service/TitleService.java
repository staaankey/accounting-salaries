package staaankey.group.accountingsalaries.titiles.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.titiles.entity.Title;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotFoundException;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotSavedException;
import staaankey.group.accountingsalaries.titiles.repos.TitleRepository;
import staaankey.group.accountingsalaries.titiles.web.dto.TitleDto;

import jakarta.transaction.Transactional;

@Service
public class TitleService {
    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public Title save(TitleDto titleDto) throws TitleNotSavedException {
        Title title = new Title();
        BeanUtils.copyProperties(titleDto, title);
        return titleRepository.save(title);
    }

    @Transactional
    public Long delete(Long id) throws TitleNotFoundException {
        if (titleRepository.deleteTitleById(id) == 0) {
            throw new TitleNotFoundException("Not found");
        } else {
            return titleRepository.deleteTitleById(id);
        }
    }

    public Title findById(Long id) throws TitleNotFoundException {
        return titleRepository.findTitleById(id);
    }
}
