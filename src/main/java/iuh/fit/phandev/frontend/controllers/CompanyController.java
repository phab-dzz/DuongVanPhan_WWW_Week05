package iuh.fit.phandev.frontend.controllers;

import com.neovisionaries.i18n.CountryCode;
import iuh.fit.phandev.backend.models.Address;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.models.Job;
import iuh.fit.phandev.backend.repoitories.*;
import iuh.fit.phandev.backend.services.ExcelExport;
import iuh.fit.phandev.backend.services.InvitationService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller

public class CompanyController {
    @Autowired
    private AddressRepository addressRepo;
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
    @GetMapping("open-signupCom")
    public String openSingupCom(Model model){
        Company company=new Company();
        if (company.getAddress() == null) {
            company.setAddress(new Address());
        }

        model.addAttribute("company",company);
        return "company/Add_Company";
    }
    @PostMapping("/SignupCom")
    public String addCompany(@ModelAttribute("company") Company company) {

        if (company.getAddress() == null) {

            company.setAddress(new Address());
        }
        if ( company.getAddress().getCountry() != null ) {
            CountryCode countryCode = CountryCode.valueOf(String.valueOf(company.getAddress().getCountry()));
            company.getAddress().setCountry(countryCode);}

        company.getAddress().setCountry(CountryCode.VN);

        Address address = new Address(
                company.getAddress().getNumber(),
                company.getAddress().getStreet(),
                company.getAddress().getCity(),
                company.getAddress().getZipcode(),
                company.getAddress().getCountry()
        );


        addressRepo.save(address);
        company.setAddress(address);
        companyReponsitory.save(company);
        return "redirect:/open-signin";
    }
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
                invitationService.sendInvitation(can.getEmail(),company.getCompName());
                mav.addObject("mess","send mail apply success");
            } catch (MessagingException e) {

                mav.addObject("mess","send mail apply fail");
            }
        }


        return mav;
    }
    @GetMapping("/export/Excel/{id}")
    public ResponseEntity<byte[]> exportToExcel(@PathVariable Long id,HttpServletRequest request) throws IOException {
        List<Candidate> candidates = candidateRepository.findCadidatesMatchWithJobs(id);


        ExcelExport excelExporter = new ExcelExport();
        byte[] excelFile = excelExporter.exportToExcel(candidates);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=candidates.xlsx");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excelFile);
    }



}
