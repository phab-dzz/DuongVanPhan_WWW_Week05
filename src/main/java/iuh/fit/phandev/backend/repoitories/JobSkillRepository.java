package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
//    @Modifying
//    @Query("delete  from JobSkill  jk where  jk.job.id= :JobID and jk.skill.id= :skillID ")
//    public boolean deleteByJobIdAndSkillId(@Param("jobID") long jobID, @Param("skillID") long skillID);

}
