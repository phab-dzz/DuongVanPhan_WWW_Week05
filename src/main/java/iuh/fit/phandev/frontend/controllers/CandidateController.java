package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.models.Address;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.repoitories.AddressRepository;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import iuh.fit.phandev.backend.services.CandidateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller

public class CandidateController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateServices candidateServices;
    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }
    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model,
                                          @RequestParam("page") Optional<Integer> page,
                                          @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage= candidateServices.findAll(
                currentPage - 1,pageSize,"id","asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("candidateById");
        try {
            modelAndView.addObject("candidates", candidateRepository.findById(1L).orElse(null));
        } catch (Exception e) {
//            logger.error("Error fetching candidate", e);
            modelAndView.addObject("candidates", null);
        }
        return modelAndView;
    }
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        candidateRepository.deleteById(id);
//        return "redirect:/candidates";
//    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        candidateRepository.deleteById(id);

        return "redirect:/list";
    }
    @PostMapping("/candidates/add")
    public String addCandidate(@ModelAttribute("candidate") Candidate candidate) {

        if (candidate.getAddress() == null) {

            candidate.setAddress(new Address());
        }


        Address address = new Address(
                candidate.getAddress().getNumber(),
                candidate.getAddress().getStreet(),
                candidate.getAddress().getCity(),
                candidate.getAddress().getZipcode(),
                candidate.getAddress().getCountry()
        );


        addressRepository.save(address);


        candidate.setAddress(address);


        candidateRepository.save(candidate);

        return "redirect:/list";
    }

    @GetMapping("/add-candidates")
    public ModelAndView showAddCandidateForm(Model model) {
        ModelAndView modelAndView   =new ModelAndView("candidates/add-candidate");

            // Kiểm tra nếu Address chưa được khởi tạo


        Candidate candidate = new Candidate();
        if (candidate.getAddress() == null) {
            candidate.setAddress(new Address());
        }

        modelAndView.addObject("candidate",candidate);

      return  modelAndView;
    }
//    @GetMapping("/openAddForm")
//    public ModelAndView openAddForm(Model model){
//        ModelAndView modelAndView = new ModelAndView();
//        Skill skill = new Skill();
//        modelAndView.addObject("skill", skill);
//        modelAndView.addObject("skillTypes", SkillType.values());
//        modelAndView.setViewName("common/addSkill");
//        return modelAndView;
//    }
//    @GetMapping("/open-signin-can")
//    public String opensigninCan(){
//        return"commons/signincan";
//    }
//    @PostMapping("/sign-in")
//    public String signin(Model model , @RequestParam("username") String usename, @RequestParam("password") String password
//                         ) {
//        Candidate candidate = null;
//
//
//            candidate = candidateRepository.findCandidateByUsernameAndPassword(usename, password).orElse(null);
//
//        if (candidate == null ){
//            model.addAttribute("mess", "Tài khoản không tồn tại!");
//            return "commons/signincan";
//        }
//        model.addAttribute("candidate", candidate);
//
//
//
//        return "commons/homecan";
//    }


}
