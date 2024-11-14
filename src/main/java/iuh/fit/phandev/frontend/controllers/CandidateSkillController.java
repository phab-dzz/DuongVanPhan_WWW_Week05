package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.enums.SkillLevel;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.CandidateSkill;
import iuh.fit.phandev.backend.models.Skill;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import iuh.fit.phandev.backend.repoitories.CandidateSkillRepository;
import iuh.fit.phandev.backend.repoitories.SkillRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/candidateSkill")
public class CandidateSkillController {
    @Autowired
    private CandidateRepository candidateRepo;
    @Autowired
    private CandidateSkillRepository candidateSkillRepo;
    @Autowired
    private SkillRepository skillRepo;
    @GetMapping("SkillOfCandidate")
    public ModelAndView getSkillOfCandidate(HttpServletRequest request) {
        Candidate candidate= (Candidate) request.getSession().getAttribute("candidateLogin");
        ModelAndView modelAndView = new ModelAndView("skills/Candidate_skill");
        List<CandidateSkill> listcanskill= candidateSkillRepo.findAllByCan_Id(candidate.getId());
        modelAndView.addObject("listcanskill", listcanskill);
        modelAndView.addObject("candidate", candidate);
        return modelAndView;
    }
    @GetMapping("/OpenAddCandidateSkill")
    public ModelAndView openAddCandidateSkill(@RequestParam("canID") long canID) {
        Candidate can= candidateRepo.findById(canID).orElse(null);
        CandidateSkill candidateSkill= new CandidateSkill();
        candidateSkill.setCan(can);
        candidateSkill.setSkill(new Skill());
        ModelAndView mav= new ModelAndView("skills/add_CanSkill");
        mav.addObject("candidateSkill", candidateSkill);
        mav.addObject("canID", canID);
        mav.addObject("skills", skillRepo.findAll());
        mav.addObject("skilllevel", SkillLevel.values());
        return mav;
    }
    @PostMapping("/addcanSkill")
    public  String addCanSkill(@ModelAttribute("candidateSkill") CandidateSkill candidateSkill) {
        try{
            candidateSkillRepo.save(candidateSkill);
        }catch(Exception e){

        }
        return "redirect:/candidateSkill/SkillOfCandidate";
    }
}
