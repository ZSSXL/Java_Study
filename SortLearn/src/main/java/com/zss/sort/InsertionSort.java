package com.zss.sort;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/12 17:12
 * @desc 插入排序
 * 1. 从第二个位置开始，跟前面的进行比较，以此类推
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] target = {112, 134, 167, 142, 102, 125};

        long start = System.currentTimeMillis();
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort(target);
        StringBuilder stringBuilder = new StringBuilder();
        for (int item : target) {
            stringBuilder.append(item).append(" ");
        }
        System.out.println("Result: [" + stringBuilder.toString() + "]");

        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");
    }

    /**
     * 插入排序
     *
     * @param target 排序对象
     */
    public void insertionSort(int[] target) {
        int temp;
        // 从第二个开始
        for (int i = 1; i < target.length; i++) {
            // 从当前位置开始，与前一个比较
            for (int j = i; j > 0; j--) {
                if (target[j] < target[j - 1]) {
                    temp = target[j];
                    target[j] = target[j - 1];
                    target[j - 1] = temp;
                }
            }
        }
    }
}
