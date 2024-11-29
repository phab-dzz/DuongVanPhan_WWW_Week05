package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.JobSkill;
import iuh.fit.phandev.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    @Query("SELECT s.skill FROM JobSkill s WHERE s.job.id = :jobId")
    List<Skill> findSkillsByJob_Id(@Param("jobId") Long jobId);

}
