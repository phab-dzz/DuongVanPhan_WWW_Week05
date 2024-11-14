package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.enums.SkillType;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Skill;
import iuh.fit.phandev.backend.repoitories.SkillRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private SkillRepository skillRepository;
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
}
