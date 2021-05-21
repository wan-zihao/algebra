package com.feather.algebraback.service.impl;

import Jama.Matrix;
import com.feather.algebraback.service.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 惊鸿之羽
 */
@Slf4j
@Service
public class OperationServiceImpl implements OperationService {

    @Override
    public String format(String s){
        //整数去掉小数部分
        if (".0".equals(s.substring(s.length() - 2))){
            s = s.substring(0,s.length() - 2);
        }
        //保留三位小数
        if (s.substring(s.indexOf('.') + 1).length() > 3){
            s = s.substring(0,s.indexOf('.') + 4);
        }
        return s;
    }

    @Override
    public double[][] transform(List<String> arrLists, boolean flag) throws Exception {
        //将List集合赋值给二维数组
        int n = arrLists.size();
        int m;
        if (n > 0){
            m = arrLists.get(0).split(" ").length;
        }else{
            return new double[0][0];
        }
        //  flag如果为true   判断是否行列数相等
        if (flag && m != n){
            throw new Exception();
        }
        double[][] arr = new double[n][m];
        int row = 0;
        //分割字符串赋值
        for (String arrList : arrLists){
            String[] s = arrList.split(" ");
            //转换出现异常说明格式有误
            for (int i = 0; i < m; i ++){
                arr[row][i] = Double.parseDouble(s[i]);
            }
            row ++;
        }
        return arr;
    }

    @Override
    public double determinantCalculationResult(double[][] arr){
        int n = arr.length;
        double res = 0;
        if (n == 1){
            return arr[0][0];
        }
        if (n == 2){
            return arr[0][0] * arr[1][1] - arr[0][1] * arr[1][0];
        }
        if (n > 2){
            //按行展开  递归计算
            for (int i = 0; i < n; i ++){
                double pow = Math.pow(-1,i);
                double[][] temp = new double[n - 1][n - 1];
                int rowT = 0, colT = 0;
                for (int j = 1; j < n; j ++){
                    for (int k = 0; k < n; k ++){
                        if (k == i){
                            continue;
                        }else{
                            if (colT == n - 1){
                                colT = 0;
                                rowT ++;
                            }
                            temp[rowT][colT ++] = arr[j][k];
                        }
                    }
                }
                res += pow * arr[0][i] * determinantCalculationResult(temp);
            }
        }
        return res;
    }

    @Override
    public List<String> matrixAdditionResult(double[][] arr1, double[][] arr2) throws Exception {
        int n1 = arr1.length, n2 = arr2.length, m1 = 0, m2 = 0;
        List<String> arrList = new ArrayList<>();
        //判断格式是否正确
        if (n1 > 0){
            m1 = arr1[0].length;
            if (n2 > 0){
                m2 = arr2[0].length;
            }else{
               throw new Exception();
            }
        }else if (n2 == 0){
            return arrList;
        }else{
            throw new Exception();
        }
        if (n1 != n2 || m1 != m2){
            throw new Exception();
        }
        //计算
        for (int i = 0; i < n1; i ++){
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < m1; j ++){
                String temp = Double.toString(arr1[i][j]+arr2[i][j]);
                temp = format(temp);
                sb.append(temp);
                if (j < m1 - 1){
                    sb.append(" ");
                }
            }
            arrList.add(new String(sb));
        }
        return arrList;
    }

    @Override
    public List<String> matrixMultiplication(double[][] arr1, double[][] arr2) throws Exception {
        int n1 = arr1.length, n2 = arr2.length, m1 = 0, m2 = 0;
        List<String> arrList = new ArrayList<>();
        if (n1 > 0){
            m1 = arr1[0].length;
            if (n2 > 0){
                m2 = arr2[0].length;
            }else{
                //n1 > 0 && n2 == 0
                throw new Exception();
            }
        }else if (n2 == 0){
            //n1 ==0 && n2 == 0
            return arrList;
        }else{
            //n1 == 0 && n2 > 0
            throw new Exception();
        }
        if (m1 != n2){
            throw new Exception();
        }
        //计算
        for (int i = 0; i < n1; i ++){
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < m2; j ++){
                double tempNum = 0;
                for (int k = 0; k < m1; k ++){
                    tempNum += arr1[i][k] * arr2[k][i];
                }
                String temp = Double.toString(tempNum);
                temp = format(temp);
                sb.append(temp);
                if (j < m2 - 1){
                    sb.append(" ");
                }
            }
            arrList.add(new String(sb));
        }
        return arrList;
    }

    @Override
    public List<String> matrixTranspose(double[][] arr) {
        int n = arr.length, m = 0;
        List<String> arrList = new ArrayList<>();
        if (n > 0){
            m = arr[0].length;
        }else {
            return arrList;
        }
        for(int i = 0; i < m; i ++){
            StringBuffer sb = new StringBuffer();
            for(int j = 0 ; j < n; j ++){
                String temp = Double.toString(arr[j][i]);
                temp = format(temp);
                sb.append(temp);
                if (j < n - 1){
                    sb.append(" ");
                }
            }
            arrList.add(new String(sb));
        }
        return arrList;
    }

    @Override
    public List<String> inverseMatrix(double[][] arr) throws Exception {
        //逆矩阵等于 矩阵行列式值的倒数乘上矩阵的伴随矩阵
        double[][] adjointMarrix = adjointMatrix(arr);
        List<String> arrList = new ArrayList<>();
        double coefficient = 1.0 / determinantCalculationResult(arr);
        int n = arr.length, m = arr[0].length;
        List<String> res = new ArrayList<>();
        if (n == 1){
            String temp = Double.toString(1.0 / arr[0][0]);
            temp = format(temp);
            res.add(temp);
            return res;
        }
        for (int i = 0; i < n; i ++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < m; j ++) {
                String temp = Double.toString(coefficient * adjointMarrix[i][j]);
                temp = format(temp);
                sb.append(temp);
                if (j < m - 1){
                    sb.append(" ");
                }
            }
            res.add(new String(sb));
        }
        return res;
    }

    @Override
    public double[][] adjointMatrix(double[][] arr) throws Exception {
        int n = arr.length;
        int m = 0;
        if (n > 0){
            m = arr[0].length;
        }else{
            return new double[0][0];
        }
        double[][] res = new double[n][m];
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                double pow = Math.pow(-1,i+j);
                double[][] doubleArrTemp = new double[n - 1][m - 1];
                int rowT = 0, colT = 0;
                //划去当前行列
                for(int k = 0; k < n; k ++){
                    for(int t = 0; t < m; t ++){
                        if (k != i && t != j){
                            doubleArrTemp[rowT][colT] = arr[k][t];
                            colT ++;
                            if (colT == m - 1){
                                rowT ++;
                                colT = 0;
                            }
                        }
                    }
                }
                //当前元素的代数余子式的值
                double resTemp = determinantCalculationResult(doubleArrTemp) * pow;
                //赋值
                res[i][j] = resTemp;
            }
        }
        //转置得到伴随矩阵
        List<String> resList = matrixTranspose(res);
        res = transform(resList, false);
        return res;
    }

    @Override
    public int matrixRank(double[][] arr) {
        return new Matrix(arr).rank();
    }

    @Override
    public String linearDependence(double[][] arr) throws Exception {
        if (arr.length == new Matrix(arr).rank()){
            return "不相关";
        }else{
            return "相关";
        }
    }

    @Override
    public double[][] eigenvalueMatrix(double[][] arr) {
        return new Matrix(arr).eig().getD().getArrayCopy();
    }

    @Override
    public String isSimilar(double[][] arr1, double[][] arr2) {
        double[][] arrayCopy1 = new Matrix(arr1).eig().getD().getArrayCopy();
        double[][] arrayCopy2 = new Matrix(arr2).eig().getD().getArrayCopy();
        double[][] array1 = new Matrix(arr1).eig().getV().getArray();
        double[][] array2 = new Matrix(arr2).eig().getV().getArray();
        for (int i = 0; i < arrayCopy1.length; i ++){
            if (!Arrays.equals(arrayCopy1[i],arrayCopy2[i])){
                return "不相似";
            }
        }
        if (array1.length != array2.length){
            return "不相似";
        }
        return "相似";
    }

    public static void main(String[] args) {
    }
}
