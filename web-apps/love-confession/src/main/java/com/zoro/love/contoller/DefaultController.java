package com.zoro.love.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: Java-Package
 * @description: A default Controller
 * @author: Zoro Li
 * @create: 2019-10-25 10:14
 */
@RestController
public class DefaultController {

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView();
        view.setViewName("forward:love.html");
        return view;
    }

}