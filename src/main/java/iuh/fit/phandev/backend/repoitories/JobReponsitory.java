package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobReponsitory extends JpaRepository<Job,Long> {

    public List<Job> findAllByCompany_Id(long id);
    @Query("select j from Job j join j.jobSkills jk join jk.skill s join s.candidateSkills ck" +
            " where ck.can.id = :candidateID and ck.can.address.city = j.company.address.city")
    public List<Job> findAllJobMatchWithCandidate(@Param("candidateID") long candidateID);
}
