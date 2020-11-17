package com.zss.sort;

import com.sun.org.apache.bcel.internal.generic.Select;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/11/12 16:22
 * @desc 选择排序
 * 1. 暂定第一个元素为最小元素, 往后遍历, 逐个与最小元素比较, 找到最小元素, 若发现最小者, 与先前的"最小元素"交换位置. 达到更新最小元素的目的
 * 2. 一趟遍历完成后, 能确保刚刚完成的这一趟遍历中, 最的小元素已经放置在前方了. 然后缩小排序范围, 新一趟排序从数组的第二个元素开始
 * 3. 在新一轮排序中重复第1、2步骤, 直到范围不能缩小为止, 排序完成
 */
@Slf4j
@SuppressWarnings("unused")
public class SelectSort {

    public static void main(String[] args) {
        int[] target = {112, 134, 167, 142, 102, 125};

        long start = System.currentTimeMillis();
        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(target);
        StringBuilder stringBuilder = new StringBuilder();
        for (int item : target) {
            stringBuilder.append(item).append(" ");
        }
        System.out.println("Result: [" + stringBuilder.toString() + "]");

        System.out.println("总耗时: [" + (System.currentTimeMillis() - start) + "]ms");
    }

    /**
     * 选择排序
     *
     * @param target 排序对象
     */
    public void selectSort(int[] target) {
        int min;
        int temp;
        for (int i = 0; i < target.length - 1; i++) {
            // 默认当前索引位置为最小值
            min = i;
            for (int j = i + 1; j < target.length; j++) {
                // 用最小的和下一个进行比较
                if (target[min] > target[j]) {
                    min = j;
                }
            }
            // 当前索引与记录的最小值的索引进行置换
            temp = target[min];
            target[min] = target[i];
            target[i] = temp;
            log.info("第{}轮排序后的数组为:{}", i + 1, target);
        }
    }
}
