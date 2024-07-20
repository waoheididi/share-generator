package com.ljh.model;

import lombok.Data;

/**
 * 静态模板配置
 * @author: ljh
 */
@Data
public class MainTemplateConfig {

    // 需求：
    // 1.在代码开头增加作者@Author注释（增加代码）
    // 2.修改程序输出的信息提示（替换代码）
    // 3.将循环读取输入改为单次读取（可选代码）

    /**
     * 作者（字符串，填充值）
     */
    private String author = "默认作者";

    /**
     * 输出信息
     */
    private String outputText = "默认输出信息";

    /**
     * 是否循环（开关）
     */
    private boolean loop;
}
