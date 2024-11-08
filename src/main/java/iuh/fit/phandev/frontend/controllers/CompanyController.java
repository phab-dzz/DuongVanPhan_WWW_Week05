package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.repoitories.CompanyReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class CompanyController {
    @Autowired
    private CompanyReponsitory companyReponsitory;
    @GetMapping("/open-signin-com")
    public String opensigninCan(){
        return"commons/signincom";
    }
    @PostMapping("/sign-in-com")
    public String signincom(Model model , @RequestParam("username") String usename, @RequestParam("password") String password
    ) {
        Company company=null;
        company = companyReponsitory.findCompanyByUsernameAndPassword(usename, password).orElse(null);

        if (company == null ){
            model.addAttribute("mess", "Tài khoản không tồn tại!");
            return "commons/signincom";
        }
        model.addAttribute("company", company);



        return "commons/homecom";
    }

}
