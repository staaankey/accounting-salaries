package staaankey.group.accountingsalaries.titles.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.titles.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Long deleteTitleById(Long id);
    Title findTitleById(Long id);
    Title findTitleBySerialNumber(String serial);
    Boolean deleteTitleBySerialNumber(String serial);
}
