package com.zss.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/16 11:29
 * @desc 归并排序 - 递归算法
 * 是一类与插入排序、交换排序、选择排序不同的另一种排序方法。
 * 归并的含义是将两个或两个以上的有序表合并成一个新的有序表。归并排序有多路归并排序、两路归并排序 ,
 * 可用于内排序，也可以用于外排序
 * 【基本原理】对于给定的一组记录，利用递归与分治算法将数据序列划分成为越来越小的半子表，
 * 在对半子表排序， 最后再用递归方法将排好序的半子表合并成为越来越大的有序序列
 * 【过程】先拆分 再聚合
 */
@SuppressWarnings("unused")
@Slf4j
public class MergeSort {

    /**
     * @param args 参数
     */
    public static void main(String[] args) {
        int[] target = {112, 134, 167, 142, 102, 125};
        // {112, 134, 167, 142, 102, 125}
        // {6, 1, 2, 7, 9, 3, 0, 4, 5, 10, 8}
        long start = System.currentTimeMillis();
        MergeSort mergeSort = new MergeSort();
        int length = target.length;
        // 用正版的方法，length要减一
        mergeSort.mergeSort(target, 0, length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int item : target) {
            stringBuilder.append(item).append(" ");
        }
        System.out.println("Result: [" + stringBuilder.toString() + "]");

        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");
    }

    /**
     * 归并排序
     *
     * @param target 排序目标
     * @param start  起始位置
     * @param end    结束位置
     */
    private void mergeSort(int[] target, int start, int end) {
        if (start < end) {
            int midValue = getMid(start, end);
            // 左边
            mergeSort(target, start, midValue);
            // 右边
            mergeSort(target, midValue + 1, end);
            // 分解完毕，进行合并
            // mergeV2(target, start, end);
            merge(target, start, midValue, end);
        }
    }

    /**
     * 合并
     *
     * @param target 合并对象
     * @param start  起始位置
     * @param mid    中间位置
     * @param end    结束位置
     */
    private void merge(int[] target, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= end) {
            if (target[i] <= target[j]) {
                temp[t++] = target[i++];
            } else {
                temp[t++] = target[j++];
            }
        }
        //将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = target[i++];
        }
        //将右序列剩余元素填充进temp中
        while (j <= end) {
            temp[t++] = target[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (start <= end) {
            target[start++] = temp[t++];
        }
    }

    /**
     * 合并 - 瞎写 - 使用插入排序
     *
     * @param target 合并对象
     * @param start  起始位置
     * @param end    结束位置
     */
    private void mergeV2(int[] target, int start, int end) {
        int temp;
        for (int i = start + 1; i < end; i++) {
            // 从当前位置开始，与前一个比较
            for (int j = i; j > start; j--) {
                if (target[j] < target[j - 1]) {
                    temp = target[j];
                    target[j] = target[j - 1];
                    target[j - 1] = temp;
                }
            }
        }
    }


    /**
     * 获取中位数
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return 中位数
     */
    private int getMid(int start, int end) {
        return (start + end) / 2;
    }
}
