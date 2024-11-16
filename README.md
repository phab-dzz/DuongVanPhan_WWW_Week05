# Xây Dựng Hệ Thống tuyển ứng viên cho công ty công
(Một số yêu cầu)
## 1. Tạo các Entity để tạo ra các bảng trong cơ sở dữ liệu
## 2. Viết các Repositories Interface
## 3. Viết các lớp Services
## . Các trang web theo yêu cầu  dành cho Công ty
## 4.Các chức năng cho công ty(nhà tuyển dụng)
### 4.1 Trang đăng nhập với vai trò là nhà tuyển dụng
![homepage.png](img%2Fhomepage.png)
![signincom.png](img%2Fsignincom.png)
### 4.2 Đăng tin tuyển dụng
![post.png](img%2Fpost.png)
![listjob.png](img%2Flistjob.png)
![jobskill.png](img%2Fjobskill.png)
### 4.3.1 Xem danh sách ứng viên phù hợp với công việc(cùng khu vực với công ty)
![jobmatchcomp.png](img%2Fjobmatchcomp.png)
![filtercandidatematchjob.png](img%2Ffiltercandidatematchjob.png)
### 4.3.2 Xem danh sách ứng viên có skill phù hợp rồi gửi mail mời(lọc theo skill mà công ty cần->Khi chọn được ứng viên thì gửi mail cho ứng viên).
![skillmatchjobComp.png](img%2FskillmatchjobComp.png)
![sendmail_success.png](img%2Fsendmail_success.png)
![mail.png](img%2Fmail.png)
### code minh hoa
![code_mail.png](img%2Fcode_mail.png)
### 4.4 Thêm skill mới
![addskill.png](img%2Faddskill.png)


## 5. Các chức năng cho ứng viên
### 5.1 Trang đăng nhập với vai trò là ứng viên
![signinCan.png](img%2FsigninCan.png)
### 5.2.1 ứng viên khi log vào sẽ được gợi ý các công việc có skill phù hợp với mình
![suggestjobwithCan.png](img%2FsuggestjobwithCan.png)
### 5.2.2 Ứng viên có thể gửi mail để ứng tuyển
![mailforCom.png](img%2FmailforCom.png)
![mailofCan.png](img%2FmailofCan.png)
## 5.3 Đề xuất một số skill mà ứng viên chưa có để học.
![suggestSkillforCan.png](img%2FsuggestSkillforCan.png)
### 5.4 Một số kỹ năng mà ứng viên đã học
![seecan_skill.png](img%2Fseecan_skill.png)
### 5.5 tìm kiếm job theo tên hoặc mô tả
![findjobwithNameAndDesc.png](img%2FfindjobwithNameAndDesc.png)

### Vì lý do github không cho push mã truy cập email nên mã truy cập sẽ được che đi-> chức năng gửi mail sẽ không hoạt động 