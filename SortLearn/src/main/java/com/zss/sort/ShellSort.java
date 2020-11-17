package com.zss.sort;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/16 11:25
 * @desc 希尔排序 - ShellSort
 * 希尔排序是基于直接插入排序的以下两点性质而提出的改进方法：
 * 1.插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
 * 2.插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
 * 3.将待排序的数组元素 按下标的一定增量分组 ，分成多个子序列，
 * 然后对各个子序列进行直接插入排序算法排序；然后依次缩减增量再进行排序，直到增量为1时，
 * 进行最后一次直接插入排序，排序结束。
 * 【注】增量的取值：（1<= increment < 待排序数组的长度）一般的初次取序列（数组）的一半为增量，以后每次减半，直到增量为1。
 */
@SuppressWarnings("unused")
public class ShellSort {

    public static void main(String[] args) {
        int[] target = {6, 1, 2, 7, 9, 3, 0, 4, 5, 10, 8};
        // {112, 134, 167, 142, 102, 125}
        // {6, 1, 2, 7, 9, 3, 0, 4, 5, 10, 8}
        long start = System.currentTimeMillis();
        ShellSort shellSort = new ShellSort();
        shellSort.shellSort(target);
        StringBuilder stringBuilder = new StringBuilder();
        for (int item : target) {
            stringBuilder.append(item).append(" ");
        }
        System.out.println("Result: [" + stringBuilder.toString() + "]");

        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");
    }

    /**
     * 希尔排序
     *
     * @param target 排序对象
     */
    private void shellSort(int[] target) {
        if (target.length != 0) {
            // 获取增量D
            for (int d = target.length / 2; d > 0; d /= 2) {
                System.out.println("增量：" + d);
                // 每次从第二个位置开始计算，i不能超过(length -1)
                for (int i = d; i < target.length; i++) {
                    // 将当前位置的值与前面位置的值进行比较，注意增量
                    for (int j = i - d; j >= 0; j -= d) {
                        if (target[j] > target[j + d]) {
                            //符合条件，插入元素（交换位置）
                            int temp = target[j];
                            target[j] = target[j + d];
                            target[j + d] = temp;
                        }
                    }
                }
            }
        }
    }
}
