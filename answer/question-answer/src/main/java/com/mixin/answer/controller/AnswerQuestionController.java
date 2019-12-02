package com.mixin.answer.controller;

import com.mixin.answer.api.IAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @description: 答题
 * @author: li baojian
 * @create: 2019/12/02 13:48
 */
@Controller
@Api(value = "答题",tags = "答题")
@RequestMapping(value = "/answer")
public class AnswerQuestionController  {

    @Autowired
    private IAnswerService answerService;

    @ResponseBody
    @ApiOperation(value = "获取答案")
    @RequestMapping(value = "/getAnswer",method = {RequestMethod.GET,RequestMethod.POST})
    public String getAnswer(String question){
        String answer = answerService.getAnswer(question);
        return answer;
    }

}
