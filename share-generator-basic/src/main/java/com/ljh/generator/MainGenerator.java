package com.ljh.generator;

import com.ljh.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 3.核心生成器(1.静态文件生成器 和 2.动态文件生成器的整合)
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");

    /** 1.生成静态文件=======START========== */
        // 输入路径：ACM 示例代码模板目录
        String inputPath = projectPath + File.separator + "share-generator-demo-projects" + File.separator + "acm-template";
        System.out.println(inputPath);
        // 输出路径：直接输出到项目的根目录
        String outputPath = projectPath;
        // 复制
        // copyFilesByHutool(inputPath, outputPath); // 现成的工具库复制目录
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath); // 递归遍历
    /** 1.生成静态文件=======END========== */


    /** 2.生成动态文件=======START========== */
        String inputDynamicFilePath = projectPath + File.separator + "share-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "share-generator-basic" + File.separator + "acm-template/src/com/ljh/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
    /** 2.生成动态文件=======START========== */

    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ljh");
        // 是否循环
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
}