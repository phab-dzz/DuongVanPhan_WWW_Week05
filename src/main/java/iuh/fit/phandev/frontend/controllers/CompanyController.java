package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import iuh.fit.phandev.backend.repoitories.CompanyReponsitory;
import iuh.fit.phandev.backend.repoitories.JobReponsitory;
import iuh.fit.phandev.backend.repoitories.JobSkillRepository;
import iuh.fit.phandev.backend.services.InvitationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class CompanyController {
    @Autowired
    private CompanyReponsitory companyReponsitory;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
private JobReponsitory jobReponsitory;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private InvitationService invitationService;

    @GetMapping("/open-signin")
    public String opensigninCan(){
        return"commons/signin";
    }
    @PostMapping("/sign-in")
    public String signincom(Model model , @RequestParam("username") String usename, @RequestParam("password") String password,
      @RequestParam("role") String role, HttpServletRequest request) {
        Candidate candidate = null;
        Company company = null;
        if (role.equalsIgnoreCase("candidate")) {
            candidate = candidateRepository.findCandidateByUsernameAndPassword(usename, password).orElse(null);
        } else {
            company = companyReponsitory.findCompanyByUsernameAndPassword(usename, password).orElse(null);
        }
        if (candidate == null && company == null) {
            model.addAttribute("mess", "Tài khoản không tồn tại!");
            return "commons/signin";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("candidateLogin", candidate);
        session.setAttribute("companyLogin", company);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }
    @GetMapping("/findCadidateMatchOfCompany")
    public ModelAndView findCadidateMatchWithJobsOfCompany(HttpServletRequest request){
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Job> jobs = jobReponsitory.findAllByCompany_Id(company.getId());
        ModelAndView mav = new ModelAndView();
       mav.addObject("jobs", jobs);
        mav.addObject("candidates",candidateRepository.findAll().subList(0,10));
        mav.setViewName("company/findCandidateJobOfCompany");
        return mav;
    }
    @PostMapping("/findCandiatewithJob")
    public  ModelAndView findCadidateMatchWithJob(@RequestParam("jobId") Long jobId,HttpServletRequest request){
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Candidate> candidates = candidateRepository.findCadidatesMatchWithJobs(jobId);

        ModelAndView mav = new ModelAndView();
        Long id =company.getId();

        List<Job> jobs = jobReponsitory.findAllByCompany_Id(company.getId());
        mav.addObject("jobs", jobs);
        mav.addObject("candidates", candidates);
        mav.setViewName("company/findCandidateJobOfCompany");
        return mav;
    }
    @PostMapping("/sendmail")
    public ModelAndView sendmailcandiate(@RequestParam("id") Long id,HttpServletRequest request) {
        Candidate can =candidateRepository.findById(id).orElse(null);
        ModelAndView mav = new ModelAndView("company/findCandidateJobOfCompany");
        Company company = (Company) request.getSession().getAttribute("companyLogin");
        List<Job> jobs = jobReponsitory.findAllByCompany_Id(company.getId());

        mav.addObject("jobs", jobs);
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
