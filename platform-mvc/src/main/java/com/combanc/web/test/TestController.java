package com.combanc.web.test;

import com.combanc.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/testweb")
public class TestController {

    @Autowired
    private TestService  testService;

    @ResponseBody
    @RequestMapping(value = "/resultOne", produces = "application/json; charset=utf-8")
    public Integer resultOne() {
        Integer result = testService.resultOne();
        return result;
    }
}
