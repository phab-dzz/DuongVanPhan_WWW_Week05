package iuh.fit.phandev.frontend.controllers;

import ch.qos.logback.core.model.Model;
import iuh.fit.phandev.backend.enums.SkillLevel;
import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.models.JobSkill;
import iuh.fit.phandev.backend.models.Skill;
import iuh.fit.phandev.backend.repoitories.JobReponsitory;
import iuh.fit.phandev.backend.repoitories.JobSkillRepository;
import iuh.fit.phandev.backend.repoitories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jobskill")
public class JobSkillController {
    @Autowired
    private JobReponsitory jobReponsitory;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @GetMapping("/openaddjobskill")
    public ModelAndView openAddJobSkill(@RequestParam("jobId") Long jobId) {
        Job job = jobReponsitory.findById(jobId).orElse(null);
        ModelAndView mav = new ModelAndView();
        JobSkill jobSkill = new JobSkill();
        jobSkill.setJob(job);
        jobSkill.setSkill(new Skill());
        mav.addObject("jobSkill", jobSkill);
        mav.addObject("skillLevel", SkillLevel.values());
        mav.addObject("skills", skillRepository.findAll());
        mav.setViewName("commons/add-jobskill");
        mav.addObject("jobId", jobId);
        return mav;
    }
    @PostMapping("/add")
    public String addjobskill(@ModelAttribute("jobSkill") JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
        return "redirect:/job/list";
    }

}
