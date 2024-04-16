package shop.jtoon.member.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.jtoon.application.SmtpService;
import shop.jtoon.member.application.LoginService;
import shop.jtoon.member.request.LocalSignUpReq;
import shop.jtoon.security.request.LoginReq;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {

	private final LoginService memberService;
	private final SmtpService smtpService;

	@PostMapping("/sign-up")
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@RequestBody @Valid LocalSignUpReq request) {
		memberService.signUp(request);
	}

	@PostMapping("/local-login")
	public void login(@RequestBody @Valid LoginReq request, HttpServletResponse servletResponse) {
		memberService.loginMember(request, servletResponse);
	}

	@GetMapping("/email-authorization")
	@ResponseStatus(HttpStatus.CREATED)
	public void authenticateEmail(@RequestParam(value = "email") String email) {
		smtpService.sendMail(email);
	}
}
