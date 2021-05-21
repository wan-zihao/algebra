package com.feather.algebraback.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feather.algebraback.domain.Answer;
import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.service.impl.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 惊鸿之羽
 */
@RestController
public class AnswerController {

    @Autowired
    AnswerServiceImpl answerService;

    /**
     * 分页查询某指定问题的回复
     *
     * @param currentPage
     * @param pageSize
     * @param questionId
     * @return
     */
    @GetMapping("/answer/{questionId}")
    public RespBean selectAnswerPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
                                     @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                     @PathVariable("questionId") Integer questionId){
        return RespBean.success(null, answerService.selectQuestionUserPage(new Page<>(currentPage,pageSize),questionId));
    }

    /**
     * 添加回复
     *
     * @param answer  添加的回复信息封装对象
     * @return      返回添加信息
     */
    @PostMapping("/answer")
    public RespBean insertAnswer(Answer answer){
        if(answerService.save(answer)){
            return RespBean.success("回复成功");
        }
        return RespBean.error("回复失败，请重新尝试");
    }

    /**
     * 更新回复信息
     *
     * @param answer  更新后数据封装
     * @param id    路径传递更新的回复id
     * @return      返回更新信息
     */
    @PutMapping("/answer/{id}")
    public RespBean updateAnswer(Answer answer,
                             @PathVariable("id") Integer id){
        if (answerService.update(answer,new UpdateWrapper<Answer>().eq("answer_id", id))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    /**
     * 删除回复
     * @param id    路径传递删除的回复id
     * @return      返回删除信息
     */
    @DeleteMapping("/answer/{id}")
    public RespBean deleteAnswer(@PathVariable("id") Integer id){
        if(answerService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

}
