package com.feather.algebraback.controller;

import com.feather.algebraback.domain.RespBean;
import com.feather.algebraback.service.impl.OperationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@RestController
public class OperationController {

    @Autowired
    private OperationServiceImpl operationService;

    /**
     * 计算行列式的值
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/determinantCalculation")
    public RespBean determinantCalculation(@RequestParam("textarea1") List<String> arrLists){
        double res = 0;
        try {
            //转换成二维数组
            double[][] arr = operationService.transform(arrLists,true);
            //调用service层计算结果
            res = operationService.determinantCalculationResult(arr);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 矩阵加法
     *
     * @param arrLists1
     * @param arrLists2
     * @return
     */
    @PostMapping("/matrixAddition")
    public RespBean matrixAddition(@RequestParam("textarea3_1") List<String> arrLists1,
                                   @RequestParam("textarea3_2") List<String> arrLists2){
        List<String> res;
        try {
            //格式转换
            double[][] arr1 = operationService.transform(arrLists1, false);
            double[][] arr2 = operationService.transform(arrLists2, false);
            //计算
            res = operationService.matrixAdditionResult(arr1,arr2);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 矩阵乘法
     *
     * @param arrLists1
     * @param arrLists2
     * @return
     */
    @PostMapping("/matrixMultiplication")
    public RespBean matrixMultiplication(@RequestParam("textarea4_1") List<String> arrLists1,
                                   @RequestParam("textarea4_2") List<String> arrLists2){
        List<String> res;
        try {
            //格式转换
            double[][] arr1 = operationService.transform(arrLists1, false);
            double[][] arr2 = operationService.transform(arrLists2, false);
            //计算
            res = operationService.matrixMultiplication(arr1,arr2);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }


    /**
     * 矩阵的转置
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/matrixTranspose")
    public RespBean matrixTranspose(@RequestParam("textarea5") List<String> arrLists){
        List<String> res;
        try {
            //格式转换
            double[][] arr = operationService.transform(arrLists, false);
            //计算
            res = operationService.matrixTranspose(arr);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 求逆矩阵
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/inverseMatrix")
    public RespBean inverseMatrix(@RequestParam("textarea6") List<String> arrLists){
        List<String> res;
        try {
            //格式转换     这里打开行列式开关是因为可逆的矩阵必须为方阵
            double[][] arr = operationService.transform(arrLists, true);
            //如果行列式值等于0 说明矩阵不可逆
            if (operationService.determinantCalculationResult(arr) == 0){
                return RespBean.error("此矩阵不可逆");
            }
            //计算
            res = operationService.inverseMatrix(arr);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 求矩阵的秩
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/matrixRank")
    public RespBean matrixRank(@RequestParam("textarea7") List<String> arrLists){
        int res;
        try {
            double[][] arr = operationService.transform(arrLists, false);
            //计算
            res = operationService.matrixRank(arr);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 判断行向量组是否线性相关
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/linearDependence")
    public RespBean linearDependence(@RequestParam("textarea8") List<String> arrLists){
        String res;
        try {
            double[][] arr = operationService.transform(arrLists, false);
            //计算
            res = operationService.linearDependence(arr);
        } catch (Exception e) {
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    /**
     * 求矩阵的特征值
     *
     * @param arrLists
     * @return
     */
    @PostMapping("/eigenvalueMatrix")
    public RespBean eigenvalueMatrix(@RequestParam("textarea9") List<String> arrLists){
        double[][] res;
        try {
            double[][] arr = operationService.transform(arrLists, false);
            //计算
            res = operationService.eigenvalueMatrix(arr);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }

    @PostMapping("/isSimilar")
    public RespBean isSimilar(@RequestParam("textarea10_1") List<String> arrLists1,
                              @RequestParam("textarea10_2") List<String> arrLists2){
        String res;
        try {
            double[][] arr1 = operationService.transform(arrLists1, false);
            double[][] arr2 = operationService.transform(arrLists2, false);
            //计算
            res = operationService.isSimilar(arr1,arr2);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("发生错误，请检查输入格式是否正确");
        }
        return RespBean.success(null,res);
    }
}
