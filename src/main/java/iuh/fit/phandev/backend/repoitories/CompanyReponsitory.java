package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CompanyReponsitory extends JpaRepository<Company, Long> {
    public boolean deleteCompanyById(long id);

}
