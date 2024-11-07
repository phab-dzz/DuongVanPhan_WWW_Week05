package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.repoitories.JobReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobReponsitory jobReponsitory;
    @GetMapping("/add-job")
    public ModelAndView addJob() {
        ModelAndView mav = new ModelAndView("job/add-job");
        mav.addObject("job", new Job());
        return mav;
    }
    @PostMapping("/add")
    public ModelAndView addJob(@ModelAttribute("job") Job job) {
        jobReponsitory.save(job);
        ModelAndView mav = new ModelAndView("redirect:/job/list");
        return mav;
    }
    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("job/listjob");
        mav.addObject("jobs", jobReponsitory.findAll());
        return mav;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteJob(@PathVariable Long id) {
        jobReponsitory.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/job/list");
        return mav;
    }
}
