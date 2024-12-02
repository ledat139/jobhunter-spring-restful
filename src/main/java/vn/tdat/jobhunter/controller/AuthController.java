package vn.tdat.jobhunter.controller;
import jakarta.validation.Valid;
import org.springframework.beans.MethodInvocationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.tdat.jobhunter.domain.dto.LoginDTO;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagementBuiler;

    public AuthController(AuthenticationManagerBuilder authenticationManagementBuiler) {
        this.authenticationManagementBuiler = authenticationManagementBuiler;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid LoginDTO login) throws MethodInvocationException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManagementBuiler.getObject().authenticate(authenticationToken);
        return ResponseEntity.ok().body(login);
    }
}
