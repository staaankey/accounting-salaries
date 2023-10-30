package staaankey.group.accountingsalaries.passports.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import staaankey.group.accountingsalaries.passports.model.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    Long deletePassportById(Long id);
    Passport findPassportById(Long id);
}
