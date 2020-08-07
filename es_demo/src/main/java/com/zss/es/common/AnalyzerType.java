package com.zss.es.common;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 10:09
 * @desc 分词类型
 */
@SuppressWarnings("unused")
public interface AnalyzerType {

    // ================= 内置分词器 start ================= //
    /**
     * 默认分词器，按词切分，小写处理
     */
    String STANDARD = "standard";
    /**
     * 按照非字母切分，小写处理
     */
    String SIMPLE = "simple";
    /**
     * 小写处理，停用词过滤(the,a,is)
     */
    String STOP = "stop";
    /**
     * 按照空格切分，不转小写
     */
    String WHITESPACE = "whitespace";
    /**
     * 不分词，直接将输入当作输出
     */
    String KEYWORD = "keyword";
    // ================= 内置分词器  end ================= //

    // ================= 中文分词器Start ================= //
    /**
     * 做最粗粒度的拆分
     */
    String IK_SMART = "ik_smart";
    /**
     * 将文本做最细粒度的拆分
     */
    String IK_MAX_WORD = "ik_max_word";
    // ================= 中文分词器  end ================= //
}
