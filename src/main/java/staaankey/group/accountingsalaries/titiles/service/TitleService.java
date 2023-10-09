package staaankey.group.accountingsalaries.titiles.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import staaankey.group.accountingsalaries.titiles.entity.Title;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotFoundException;
import staaankey.group.accountingsalaries.titiles.exception.TitleNotSavedException;
import staaankey.group.accountingsalaries.titiles.repos.TitleRepository;
import staaankey.group.accountingsalaries.titiles.web.dto.TitleDto;

@Service
public class TitleService {
    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public Integer save(TitleDto titleDto) throws TitleNotSavedException {
        Title title = new Title();
        BeanUtils.copyProperties(titleDto, title);

        try {
            return titleRepository.save(title);
        } catch (TitleNotSavedException e) {
            throw new TitleNotSavedException("Title not saved!");
        }
    }

    public Integer delete(int id) throws TitleNotFoundException {
        if (titleRepository.delete(id) == 0) {
            throw new TitleNotFoundException("Not found");
        } else {
            return titleRepository.delete(id);
        }
    }

    public Title findById(int id) throws TitleNotFoundException {
        return titleRepository.findById(id);
    }
}
