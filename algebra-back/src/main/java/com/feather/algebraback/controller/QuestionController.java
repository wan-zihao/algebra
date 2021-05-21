package com.feather.algebraback.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Message;
import com.feather.algebraback.domain.Question;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.domain.VO.QuestionUserVO;
import com.feather.algebraback.service.impl.MessageServiceImpl;
import com.feather.algebraback.service.impl.QuestionServiceImpl;
import com.feather.algebraback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author 惊鸿之羽
 */
@RestController
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    MessageServiceImpl messageService;

    /**
     * 分页查询所有问题
     *
     * @param currentPage   当前页码
     * @param pageSize      每页大小
     * @param search        查询参数
     * @return
     */
    @GetMapping("/question")
    public RespBean selectQuestionUserPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "search",defaultValue = "") String search){
        return RespBean.success(null, questionService.selectQuestionUserPage(new Page<QuestionUserVO>(currentPage,pageSize),search));
    }

    /**
     * 添加问题
     * @param question  添加的问题信息封装对象
     * @return      返回添加信息
     */
    @PostMapping("/question")
    public RespBean insertQuestion(Question question,
                                 Principal principal){
        if (principal == null) {
            return RespBean.error("尚未登录,请先登录",null);
        }
        question.setUserId(userService.getUserByUserName(principal.getName()).getUserId());
        if(questionService.save(question)){
            messageService.save(new Message("id为" + question.getUserId() + "的用户发布了文章<<" + question.getQuestionTitle() + ">>"));
            return RespBean.success("发布成功",null);
        }
        return RespBean.error("出现问题，请重新尝试");
    }

    /**
     * 删除问题
     * @param id    路径传递删除的问题id
     * @return      返回删除信息
     */
    @DeleteMapping("/question/{id}")
    public RespBean deleteQuestion(@PathVariable("id") Integer id){
        if(questionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
