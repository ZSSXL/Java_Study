package com.zss.sort;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/12 15:13
 * @desc 冒泡排序
 * 1. 从后往前依次比较相邻的元素。若是要按照升序排序，则后面的元素比前面的小，就交换这2个元素；降序则相反。
 * 2. 对每一对相邻元素作同样的工作，从第一对到最后一对。进行一轮比较交换下来，最后的元素就会是最小（或最大）的数了，这个数就不用参与后面的比较操作了。
 * 3. 针对所有的元素重复以上的步骤。
 * 4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
@SuppressWarnings("unused")
public class BubbleSort {

    public static void main(String[] args) {
        int[] target = {118, 101, 127, 112, 114};
        long start = System.currentTimeMillis();
        BubbleSort bubbleSort = new BubbleSort();
        // bubbleSort.bubbleSortSimple(target)
        bubbleSort.bubbleSortUpgrade(target);
        StringBuilder sb = new StringBuilder();
        for (int item : target) {
            sb.append(item).append(" ");
        }
        System.out.println("Result: [" + sb.toString() + "]");
        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");

    }

    /**
     * 冒泡排序 - 简易版本
     *
     * @param target 排序对象
     */
    public void bubbleSortSimple(int[] target) {
        int temp;
        int flag = 0;
        for (int i = 0; i < target.length - 1; i++) {
            for (int j = 0; j < target.length - 1; j++) {
                flag++;
                if (target[j] > target[j + 1]) {
                    temp = target[j + 1];
                    target[j + 1] = target[j];
                    target[j] = temp;
                }
            }
        }
        System.out.println("Loop Times: [" + flag + "]");
    }

    /**
     * 冒泡排序 - 升级版
     * 减去之前已经排好的次数
     *
     * @param target 排序目标
     */
    public void bubbleSortUpgrade(int[] target) {
        int temp;
        int flag = 0;
        for (int i = 0; i < target.length - 1; i++) {
            for (int j = 0; j < target.length - i - 1; j++) {
                flag++;
                if (target[j] > target[j + 1]) {
                    temp = target[j + 1];
                    target[j + 1] = target[j];
                    target[j] = temp;
                }
            }
        }
        System.out.println("Loop Times: [" + flag + "]");
    }

    /**
     * 冒泡排序 - Java版本
     *
     * @param target 排序目标
     * @return 结果
     */
    public List<Integer> bubbleSortJava(List<Integer> target) {
        return target.stream().sorted().collect(Collectors.toList());
    }

}
