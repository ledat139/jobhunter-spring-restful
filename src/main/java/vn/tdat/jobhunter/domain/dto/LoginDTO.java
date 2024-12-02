package vn.tdat.jobhunter.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "Không được để trống tên đăng nhập")
    private String username;
    @NotBlank(message = "Không được để trống mật khẩu")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
