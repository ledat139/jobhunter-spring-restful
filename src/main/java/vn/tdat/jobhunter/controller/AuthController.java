package vn.tdat.jobhunter.controller;
import jakarta.validation.Valid;
import org.springframework.beans.MethodInvocationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.proc.SecurityContext;

import vn.tdat.jobhunter.domain.dto.LoginDTO;
import vn.tdat.jobhunter.domain.dto.ResLoginDTO;
import vn.tdat.jobhunter.util.SecurityUtil;

@RestController
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagementBuiler;
    private final SecurityUtil securityUtil;

    public AuthController(AuthenticationManagerBuilder authenticationManagementBuiler, SecurityUtil securityUtil) {
        this.authenticationManagementBuiler = authenticationManagementBuiler;
        this.securityUtil = securityUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody @Valid LoginDTO login) throws MethodInvocationException, Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManagementBuiler.getObject().authenticate(authenticationToken);
        String accessToken = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResLoginDTO resLoginDTO = new ResLoginDTO();
        resLoginDTO.setAccessToken(accessToken);
        return ResponseEntity.ok().body(resLoginDTO);
    }
}
