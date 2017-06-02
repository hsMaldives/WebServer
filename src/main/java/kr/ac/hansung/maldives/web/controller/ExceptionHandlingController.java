package kr.ac.hansung.maldives.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(Exception.class)
  public String handleError(HttpServletRequest req, HttpServletResponse response, Exception ex, Model model) {
    log.warn("[예외발생] (statusCode: {}, Request: {}, Exception: {})", response.getStatus(), req.getRequestURL(), ex);
    
    model.addAttribute("statusCode", response.getStatus());
    
    return "error/exception";
  }
}