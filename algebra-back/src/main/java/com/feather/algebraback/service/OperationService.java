package com.feather.algebraback.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Service
public interface OperationService {

    /**
     * string类型的list集合转换成二维数组
     *
     * @param arrList
     * @param flag  true表示转换成行列式，false表示转换为矩阵
     * @return
     * @throws Exception
     */
    public double[][] transform(List<String> arrList, boolean flag) throws Exception;

    /**
     * 行列式求值
     * @param arr
     * @return
     */
    public double determinantCalculationResult(double[][] arr);

    /**
     * 计算矩阵加法
     *
     * @param arr1
     * @param arr2
     * @return
     * @throws Exception
     *
     */
    List<String> matrixAdditionResult(double[][] arr1, double[][] arr2) throws Exception;

    /**
     * 计算矩阵乘法
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public List<String> matrixMultiplication(double[][] arr1, double[][] arr2) throws Exception;


    /**
     * 矩阵的转置
     *
     * @param arr
     * @return
     */
    List<String> matrixTranspose(double[][] arr);

    /**
     * 求逆矩阵
     *
     * @param arr
     * @return
     */
    List<String> inverseMatrix(double[][] arr) throws Exception;

    /**
     * 转换成伴随矩阵
     *
     * @param arr
     * @return
     */
    double[][] adjointMatrix(double[][] arr) throws Exception;

    /**
     * 格式化字符串
     *
     * @param s
     * @return
     */
    String format(String s);

    /**
     * 求矩阵的秩
     *
     * @param arr
     * @return
     */
    int matrixRank(double[][] arr);

    /**
     * 判断向量组是否线性相关
     *
     * @param arr
     * @return
     */
    String linearDependence(double[][] arr) throws Exception;

    /**
     * 求矩阵的特征值
     *
     * @param arr
     * @return
     */
    double[][] eigenvalueMatrix(double[][] arr);

    /**
     * 判断两个矩阵是否相似
     *
     * @param arr1
     * @param arr2
     * @return
     */
    String isSimilar(double[][] arr1, double[][] arr2);
}
