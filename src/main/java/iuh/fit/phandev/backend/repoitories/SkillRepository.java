package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    public boolean deleteSkillById(long id);
    @Query("select s from Skill s where s.id not in (select cs.skill.id from CandidateSkill cs where cs.can.id " +
            " = :candidateID)")
    public List<Skill> findSkillCandidateShouldLearn(@Param("candidateID") long candidateID);
}
