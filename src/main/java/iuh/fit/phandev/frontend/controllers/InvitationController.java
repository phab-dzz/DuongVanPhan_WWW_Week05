package iuh.fit.phandev.frontend.controllers;

import iuh.fit.phandev.backend.services.InvitationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvitationController {
    @Autowired
    private InvitationService invitationService;

    @PostMapping("/send-invitation")
    public String sendInvitation(
            @RequestParam String email,
            @RequestParam String name) {
        try {
            invitationService.sendInvitation(email, name);
            return "Invitation email sent successfully!";
        } catch (MessagingException e) {
            return "Error sending invitation email: " + e.getMessage();
        }
    }
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
