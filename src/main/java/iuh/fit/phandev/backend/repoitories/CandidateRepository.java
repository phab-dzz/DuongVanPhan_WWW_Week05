package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    public Optional<Candidate> findCandidateByUsernameAndPassword(String username, String password);

    @Query("select c from Candidate c join c.candidateSkills ck join ck.skill s join s.jobSkills js join js.job j " +
            "where j.id = :jobID and c.address.city = j.company.address.city")
    public List<Candidate> findCadidatesMatchWithJobs(@Param("jobID") long jobID);
    @Query("select c from Candidate c join c.candidateSkills ck join ck.skill s join s.jobSkills js join js.job j " +
            "where s.id = :SkillID and c.address.city = j.company.address.city")
    public List<Candidate> findCadidatesMatchWithSkills(@Param("SkillID") long SkillID);
    @Query("SELECT c FROM Candidate c JOIN c.candidateSkills cs JOIN cs.skill s WHERE s IN :skills")
    List<Candidate> findCandidatesBySkills(@Param("skills") List<Skill> skills);



}
