package com.feather.algebraback.operation;

import Jama.Matrix;

import java.util.Arrays;

public class operationTest {
    public static double determinantCalculationResult(double[][] arr){
        int n = arr.length;
        double res = 0;
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

    public static void main(String[] args) {
        double[][] arr1 = {{1,2},{2,4}};
        double[][] arr2 = {{1,2},{2,4}};
        System.out.println(arr1 == arr2);
        //for (int i = 0; i < 2; i ++){
        //    System.out.println(Arrays.equals(arr1[i],arr2[i]));
        //}
    }
}
