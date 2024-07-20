package com.ljh.generator;

import cn.hutool.core.io.FileUtil;
import com.ljh.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 2.动态文件生成
 */
public class DynamicGenerator {

    /** 方案1：全逻辑 */
    // public static void main(String[] args) throws IOException, TemplateException {
    //     // new 出 Configuration 对象，参数为 FreeMarker 版本号
    //     Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
    //
    //
    //     // G:\demo\Java\dailystudy\person\share-generator\share-generator_backed
    //     String property = System.getProperty("user.dir");
    //     String projectPath = property + File.separator + "share-generator-basic";
    //     File parentFile = new File(projectPath);
    //
    //     File file = new File(parentFile, "src/main/resources/templates");
    //     // System.out.println(file.getAbsoluteFile());
    //
    //     // 指定模板文件所在的路径
    //     configuration.setDirectoryForTemplateLoading(file);
    //
    //     // 设置模板文件使用的字符集
    //     configuration.setDefaultEncoding("utf-8");
    //     configuration.setNumberFormat("0.##########");
    //
    //     // 创建模板对象，加载指定模板
    //     Template template = configuration.getTemplate("MainTemplate.java.ftl");
    //     // 创建数据模型
    //     MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
    //     mainTemplateConfig.setAuthor("ljh");
    //     // 不使用循环
    //     mainTemplateConfig.setLoop(false);
    //     mainTemplateConfig.setOutputText("求和结果:");
    //     // 生成文件
    //     Writer out = new FileWriter("MainTemplate.java");
    //     template.process(mainTemplateConfig, out);
    //     // 关闭流
    //     out.close();
    // }

    /** 方案2：抽象 */
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir") + File.separator + "share-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ljh");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(inputPath, outputPath, mainTemplateConfig);
    }

    /**
     * 生成文件
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        System.out.println(templateDir);
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 创建数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("ljh");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");


        // 文件不存在则创建文件和父目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }

}