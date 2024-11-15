package iuh.fit.phandev.backend.services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    @Autowired
    private EmailService emailService;

    public void sendInvitation(String recipientEmail, String senderName) throws MessagingException {
        String subject = "Mời bạn ứng tuyển công việc tại công ty chúng tôi";
        String body = "<h3>Chào bạn,</h3>" +
                "<p>Chúng tôi rất vui khi thông báo rằng bạn đã được mời tham gia ứng tuyển vào vị trí tại công ty chúng tôi.</p>" +
                "<p>Xin vui lòng nhấp vào <a href='https://github.com/phab-dzz/DuongVanPhan_WWW_Week05'>đây</a> để hoàn tất quá trình ứng tuyển và tìm hiểu thêm chi tiết về công việc.</p>" +
                "<p>Chúc bạn may mắn và hy vọng được làm việc cùng bạn.</p>" +
                "<p>Trân trọng,</p>" +
                "<p>" + senderName + "</p>";

        emailService.sendInvitationEmail(recipientEmail, subject, body);
    }
}
