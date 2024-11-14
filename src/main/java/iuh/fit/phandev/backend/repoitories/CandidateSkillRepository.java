package iuh.fit.phandev.backend.repoitories;

import iuh.fit.phandev.backend.models.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {

    @Modifying
    @Query("DELETE FROM CandidateSkill CK where CK.can.id = :canID and CK.skill.id = :skillID")
    public boolean deleteCandidateSkillByCandidateAndSkill(@Param("canID") long canID, @Param("skillID") long skillID);
    public List<CandidateSkill>  findAllByCan_Id(long candidateID);

}
