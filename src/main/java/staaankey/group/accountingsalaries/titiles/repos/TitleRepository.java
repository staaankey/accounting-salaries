package staaankey.group.accountingsalaries.titiles.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.titiles.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    Long deleteTitleById(Long id);
    Title findTitleById(Long id);

}
