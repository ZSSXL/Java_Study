package com.zss.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/12 16:22
 * @desc 快速排序
 * 二分法
 */
@Slf4j
@SuppressWarnings("unused")
public class QuickSort {

    public static void main(String[] args) {
        // {112, 134, 167, 142, 102, 125}
        // {6, 1, 2, 7, 9, 3, 0, 4, 5, 10, 8}
        int[] target = {6, 1, 2, 7, 9, 3, 0, 4, 5, 10, 8};
        long start = System.currentTimeMillis();
        QuickSort quickSort = new QuickSort();
        quickSort.quickSortV2(target, 0, target.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int item : target) {
            stringBuilder.append(item).append(" ");
        }
        System.out.println("Result: [" + stringBuilder.toString() + "]");

        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");
    }

    /**
     * 快速排序
     *
     * @param target 排序对象
     * @param left   左边 -> 默认左边第一个为基准点
     * @param right  右边
     */
    public void quickSort(int[] target, int left, int right) {
        if (left < right) {
            // 基准点
            int datum = target[left];
            // 左哨兵
            int i = left;
            // 右哨兵
            int j = right;

            int temp;
            // 当左哨兵小于右哨兵时，进行下一步
            while (i < j) {
                // 从右往左，找到小于基准点的位置
                while (i < j && target[j] > datum) {
                    j--;
                }
                if (i < j) {
                    target[i] = target[j];
                    i++;
                }
                // 从左往右，找到大于基准点的位置
                while (i < j && target[i] < datum) {
                    i++;
                }
                if (i < j) {
                    target[j] = target[i];
                    j--;
                }
            }
            target[i] = datum;

            // 快速排序左边部份
            quickSort(target, left, i - 1);
            // 快速排序右边部份
            quickSort(target, i + 1, right);
        }
    }

    /**
     * 快速排序
     *
     * @param target 排序对象
     * @param left   左边 -> 默认左边第一个为基准点
     * @param right  右边
     */
    public void quickSortV2(int[] target, int left, int right) {
        // 当左哨兵小于右哨兵时，进行下一步
        if (left < right) {
            // 基准点
            int datum = target[left];
            // 左哨兵
            int i = left;
            // 右哨兵
            int j = right;

            int temp;

            while (i < j) {
                // 从右往左，找到小于基准点的位置
                while (target[j] >= datum && i < j) {
                    j--;
                }
                // 从左往右，找到大于基准点的位置
                while (target[i] <= datum && i < j) {
                    i++;
                }
                // 都准备就绪后，开始进行交换，如果i和j相等，就没必要交换
                if (i < j) {
                    temp = target[i];
                    target[i] = target[j];
                    target[j] = temp;
                }
            }
            // 当i和j相遇时，将left位置与i位置交换，i位置与基准点交换
            target[left] = target[i];
            target[i] = datum;

            // 快速排序左边部份
            quickSortV2(target, left, i - 1);
            // 快速排序右边部份
            quickSortV2(target, i + 1, right);
        }
    }


}
