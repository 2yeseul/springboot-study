package com.studyolle.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;

    @InitBinder("signUpForm") // camel 같은 것들 따라감
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        // model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute(new SignUpForm()); // 이름 camel식 같은 표현 -> 생략가능
        return "account/sign-up";
    }

    // 파라미터로 쓸 때 @ModelAttribute 생략가
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if(errors.hasErrors())
            return "account/sign-up";

        // TODO 회원 가입 처리
       return "redirect:/";

    }

}
