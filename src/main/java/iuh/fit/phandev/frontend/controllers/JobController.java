package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.repoitories.JobReponsitory;
import iuh.fit.phandev.backend.repoitories.JobSkillRepository;
import iuh.fit.phandev.backend.services.InvitationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobReponsitory jobReponsitory;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    private InvitationService invitationService;
    @Autowired
    private iuh.fit.phandev.backend.services.invitationCom invitationCom;

    @GetMapping("/add-job")
    public ModelAndView addJob() {
        ModelAndView mav = new ModelAndView("job/add-job");
        mav.addObject("job", new Job());
        return mav;
    }
    @PostMapping("/add")
    public ModelAndView addJob(@ModelAttribute("job") Job job, HttpServletRequest request) {
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        job.setCompany(company);
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
    @PostMapping("/edit")
    public ModelAndView editJob(@ModelAttribute Job job) {
        ModelAndView mav = new ModelAndView("redirect:/job/list");

        jobReponsitory.save(job);

        return mav;
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Job job = jobReponsitory.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
        model.addAttribute("jobid", job);
        return "job/listjob";
    }
    @GetMapping("/findJobWithcan")
    public ModelAndView findJobWithCan(HttpServletRequest request) {
        Candidate can= (Candidate)request.getSession().getAttribute("candidateLogin");
        List<Job> listjobs=jobReponsitory.findAllJobMatchWithCandidate(can.getId());
        ModelAndView mav = new ModelAndView("job/JobWithCan");
        mav.addObject("jobs", listjobs);
        return mav;

    }
    @GetMapping("/advanceJobMatch")
    public ModelAndView findAdvanceJob(@RequestParam("value") String value) {
        ModelAndView mav = new ModelAndView("job/listjob");
        List<Job> listjob=jobReponsitory.findJobsByAdvanceJobMatch(value);
        mav.addObject("jobs", listjob);
        return mav;
    }
    @PostMapping("/sendmailcompany")
    public ModelAndView sendmailcompany(@RequestParam("id") Long id,HttpServletRequest request) {
        Job job =jobReponsitory.findById(id).orElse(null);
        ModelAndView mav = new ModelAndView("job/JobWithCan");
        Candidate can = (Candidate) request.getSession().getAttribute("candidateLogin");
        List<Job> listjobs=jobReponsitory.findAllJobMatchWithCandidate(can.getId());
        mav.addObject("jobs", listjobs);


        if(can != null){
            try {
                invitationCom.sendInvitation(job.getCompany().getEmail(),can.getFullName());
                mav.addObject("mess","send mail apply success");
            } catch (MessagingException e) {

                mav.addObject("mess","send mail apply fail");
            }
        }


        return mav;
    }



}
