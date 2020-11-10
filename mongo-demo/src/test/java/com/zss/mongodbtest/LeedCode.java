package com.zss.mongodbtest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/9 19:23
 * @desc LeedCode算法题目 - 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 */
public class LeedCode {

    private final static BigDecimal ZERO = new BigDecimal("0");
    private final static BigDecimal OOO = new BigDecimal("0.0");
    private final static BigDecimal LLL = new BigDecimal("1.1111111");

    public static void main(String[] args) {

        int[][] points1 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}; // 4
        int[][] points2 = {{1, 1}, {2, 2}, {3, 3}, {4, 4}}; // 4
        int[][] points3 = {{1, 1}, {2, 3}, {4, 2}, {5, 4}}; // 2
        int[][] points4 = {}; // 0
        int[][] points5 = {{0, 0}, {-1, -2}, {2, 2}, {3, 4}}; // 2
        int[][] points6 = {{2, 3}, {3, 3}, {-5, 3}, {1, 2}, {0, 1}, {0, 3}}; // 4
        int[][] points7 = {{1, 1}, {1, 1}, {1, 1}, {2, 3}}; // 4
        int[][] points8 = {{-5, 3}, {-4, 2}, {-3, 1}, {-3, 3}}; // 3
        int[][] points9 = {{1, 1}, {1, 1}, {1, 1}}; // 3
        int[][] points10 = {{-5, -2}, {-3, -1}, {-1, 0}, {1, 1}, {3, 2}}; // 5
        int[][] points11 = {{1, 1}, {1, 1}, {2, 3}, {2, 4}}; // 3
        int[][] points12 = {{0, 0}, {1, 1}, {1, -1}}; // 2
        int[][] points13 = {{0, 0}, {94911150, 94911151}, {94911151, 94911152}}; // 2

        List<int[][]> points = new ArrayList<>();
        points.add(points1);
        points.add(points2);
        points.add(points3);
        points.add(points4);
        points.add(points5);
        points.add(points6);
        points.add(points7);
        points.add(points8);
        points.add(points9);
        points.add(points10);
        points.add(points11);
        points.add(points12);
        points.add(points13);

        long start = System.currentTimeMillis();
        LeedCode learnTest = new LeedCode();
        for (int[][] point : points) {
            int result = learnTest.maxPoints(point);
            System.out.println("Result : [" + result + "]");
        }

        System.out.println("总耗时：" + (System.currentTimeMillis() - start) + " ms");
    }

    private int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        return calculation(points);
    }

    /**
     * 计算
     *
     * @param pointList 节点列表
     * @return 结果
     */
    private int calculation(int[][] pointList) {
        int count = 0;
        // 临时
        int temp = 0;
        // 比值
        BigDecimal ratio;
        for (int[] point : pointList) {
            for (int[] item : pointList) {
                ratio = getRatio(point, item);
                for (int[] p : pointList) {
                    if (ratio.equals(getRatio(point, p))) {
                        temp++;
                    } else if (point[0] == p[0] && point[1] == p[1]) {
                        temp++;
                    }
                }
                count = judge(count, temp);
                temp = 0;
            }
        }
        return count;
    }

    /**
     * 判断
     *
     * @param count 计数
     * @param temp  临时计数
     * @return count
     */
    private int judge(int count, int temp) {
        if (temp > count) {
            count = temp;
        }
        return count;
    }

    /**
     * 比值
     *
     * @param p1 源节点
     * @param p2 对比节点
     * @return BigDecimal
     */
    private BigDecimal getRatio(int[] p1, int[] p2) {
        BigDecimal x = new BigDecimal(p1[0] - p2[0]);
        BigDecimal y = new BigDecimal(p1[1] - p2[1]);
        if (x.equals(ZERO) && !y.equals(ZERO)) {
            return OOO;
        } else if (!x.equals(ZERO) && y.equals(ZERO)) {
            return LLL;
        } else if (x.equals(ZERO) && y.equals(ZERO)) {
            return OOO;
        } else {
            return x.divide(y, 16, RoundingMode.HALF_UP).stripTrailingZeros();
        }
    }

}

