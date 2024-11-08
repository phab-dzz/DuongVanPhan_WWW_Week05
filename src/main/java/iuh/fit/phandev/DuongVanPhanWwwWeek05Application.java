package iuh.fit.phandev;

import com.neovisionaries.i18n.CountryCode;
import iuh.fit.phandev.backend.models.Address;
import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.models.Company;
import iuh.fit.phandev.backend.repoitories.AddressRepository;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import iuh.fit.phandev.backend.repoitories.CompanyReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class DuongVanPhanWwwWeek05Application {

    public static void main(String[] args) {
        SpringApplication.run(DuongVanPhanWwwWeek05Application.class, args);
    }
//    @Autowired
//    private CandidateRepository candidateRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private CompanyReponsitory companyReponsitory;
//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//            Random rnd =new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address(rnd.nextInt(1,1000)+"","Quang Trung","HCM",
//                        rnd.nextInt(70000,80000)+"", CountryCode.VN );
//                addressRepository.save(add);
////                Candidate can=new Candidate("Name #"+i,
////                        LocalDate.of(1998,rnd.nextInt(1,13),rnd.nextInt(1,29)),
////                        add,
////                        rnd.nextLong(1111111111L,9999999999L)+"",
////                        "email_"+i+"@gmail.com","candidate"+i,"password"+i);
////                candidateRepository.save(can);
//                Company com = new Company( add, "company #didong"+i,"about #"+i, "email_"+i+"@gmail.com",rnd.nextLong(1111111111L,9999999999L)+"", "https://www."+"company"+i+".com", "company"+i,"password"+i);
//                companyReponsitory.save(com);
//                System.out.println("Added: " +com);
//            }
//        };
//    }
}
