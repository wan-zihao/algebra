package com.feather.algebraback.controller;

import com.feather.algebraback.service.impl.AnswersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 惊鸿之羽
 */
@RestController
public class AnswersController {
    @Autowired
    AnswersServiceImpl answersService;
}
