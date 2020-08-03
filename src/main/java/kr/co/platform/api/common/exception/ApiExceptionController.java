package kr.co.platform.api.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.platform.util.advice.exception.AuthenticationEntryPointException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/exception")
public class ApiExceptionController {

    @RequestMapping(value = "/entrypoint")
    public ResponseEntity<String> entrypointException() {
        throw new AuthenticationEntryPointException();
    }

    @RequestMapping(value = "/accessdenied")
    public ResponseEntity<String> accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
