package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.enums.SkillType;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.models.Skill;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import iuh.fit.phandev.backend.repoitories.SkillRepository;
import iuh.fit.phandev.backend.services.InvitationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private InvitationService invitationService;

    @GetMapping("/openformadd")
    public ModelAndView openAddForm(){
        ModelAndView modelAndView = new ModelAndView();
        Skill skill = new Skill();
        modelAndView.addObject("skill", skill);
        modelAndView.addObject("skillTypes", SkillType.values());
        modelAndView.setViewName("skills/add-skill");
        return modelAndView;
    }
    @PostMapping("/add")
    public String insertSkill(@ModelAttribute("skill") Skill skill, Model model){
        try {
            skillRepository.save(skill);
            model.addAttribute("stt", "Thêm thành công!");

        } catch (Exception e){
            model.addAttribute("stt", "Thêm thất bại!");
        }
        return "skills/add-skill";
    }
    @GetMapping("/findSkillLearn")
    public ModelAndView findSkillLearn(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Candidate can=(Candidate) request.getSession().getAttribute("candidateLogin");
        List<Skill> skills= skillRepository.findSkillCandidateShouldLearn(can.getId());
        modelAndView.addObject("skills", skills);
        modelAndView.setViewName("skills/KillsLearn");
        return modelAndView;
    }
    @GetMapping("/findCadidateskillMatchOfCompany")
    public ModelAndView findCadidateMatchWithJobsOfCompany(HttpServletRequest request){
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Skill> skills= skillRepository.findSkillByCompany(company.getId());
        ModelAndView mav = new ModelAndView();
        mav.addObject("skills", skills);
        mav.addObject("candidates",candidateRepository.findAll().subList(0,10));
        mav.setViewName("company/findCandidateSkillOfCompany");
        return mav;
    }
    @PostMapping("/findCandiatewithskill")
    public  ModelAndView findCadidateMatchWithJob(@RequestParam("skillId") Long skillId, HttpServletRequest request){
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Candidate> candidates = candidateRepository.findCadidatesMatchWithSkills(skillId);

        ModelAndView mav = new ModelAndView();
        Long id =company.getId();

        List<Skill> skills= skillRepository.findSkillByCompany(company.getId());
        mav.addObject("skills", skills);
        mav.addObject("candidates", candidates);
        mav.setViewName("company/findCandidateSkillOfCompany");
        return mav;
    }
    @PostMapping("/sendmail")
    public ModelAndView sendmailcandiate(@RequestParam("id") Long id,HttpServletRequest request) {
        Candidate can =candidateRepository.findById(id).orElse(null);
        ModelAndView mav = new ModelAndView("company/findCandidateSkillOfCompany");
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Skill> skills= skillRepository.findSkillByCompany(company.getId());

        mav.addObject("skills", skills);
        mav.addObject("candidates",candidateRepository.findAll().subList(0,10));
        if(can != null){
            try {
                invitationService.sendInvitation(can.getEmail(),can.getFullName());
                mav.addObject("mess","send mail apply success");
            } catch (MessagingException e) {

                mav.addObject("mess","send mail apply fail");
            }
        }


        return mav;
    }
}
