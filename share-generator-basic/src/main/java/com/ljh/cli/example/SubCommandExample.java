package com.ljh.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 1.Picocli命令行框架入门 -> 3.子命令
 *
 * 支持主命令、帮助、增加、删除、查询5个子命令，并传入不同的args来测试效果
 */
@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {

    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddCommand implements Runnable {
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @Command(name = "delete", description = "删除", mixinStandardHelpOptions = true)
    static class DeleteCommand implements Runnable {
        public void run() {
            System.out.println("执行删除命令");
        }
    }

    @Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QueryCommand implements Runnable {
        public void run() {
            System.out.println("执行查询命令");
        }
    }

    public static void main(String[] args) {
        // （1）执行主命令
        String[] myArgs = new String[] { };
        // （2）查看主命令的帮助手册
       // String[] myArgs = new String[] { "--help" };
        // （3）执行增加命令
//        String[] myArgs = new String[] { "add" };
        // （4）执行增加命令的帮助手册
//        String[] myArgs = new String[] { "add", "--help" };
        // （5）执行不存在的命令，会报错
//        String[] myArgs = new String[] { "update" };
        int exitCode = new CommandLine(new SubCommandExample())
                .addSubcommand(new AddCommand())
                .addSubcommand(new DeleteCommand())
                .addSubcommand(new QueryCommand())
                .execute(myArgs);
        System.exit(exitCode);
    }
}