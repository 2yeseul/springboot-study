package com.studyolle.account;

import com.studyolle.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor // private final 멤버 생성자 만들어줌 (private은 안만들어줌)
public class SignUpFormValidator implements Validator {
// final 빼뜨리면 안됨
    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }
 
    @Override
    public void validate(Object object, Errors errors) {
        // TODO email, nickname
        SignUpForm signUpForm = (SignUpForm)object;
        if(accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일 입니다.");
        }
        if(accountRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }
    }
}
