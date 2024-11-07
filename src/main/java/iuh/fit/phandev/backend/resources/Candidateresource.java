package iuh.fit.phandev.backend.resources;

import iuh.fit.phandev.backend.models.Candidate;
import iuh.fit.phandev.backend.repoitories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/candidates")
public class Candidateresource {
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("all")
    public List<Candidate> getall(){
        return candidateRepository.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        try {
            candidateRepository.deleteById(id); // Xóa ứng viên theo ID
            return ResponseEntity.ok("Xóa ứng viên thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi xóa ứng viên: " + e.getMessage());
        }
    }
}
