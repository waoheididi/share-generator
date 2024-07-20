package com.ljh.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 *
 * 1.Picocli命令行框架入门 -> 2.交互式输入
 * （1）所谓的交互式输入就是允许用户像限程序聊天一样，在程序的指引下一个参数一个参数地输入。
 *     交互式输入，在Picocli中就要求是实现Callable接口。Callable<Integer> => Integer执行程序后的返回值
 *
 * （2）@Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true)：
 *          names = {"-p", "--password"}    =>  -p:简写 --password:全称
 *          arity = "0..1"                  =>  0..1 表示参数个数，0个或1个，就变成了可选交互式
 *          description = "Passphrase"      =>  描述
 *          interactive = true              =>  是否允许交互式输入
 *
 * （3）在所有参数都输入完成后，会执行 call 方法，可以在该方法中编写具体的业务逻辑
 * （4）在Main方法中执行命令，并传入参数
 */
public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    // 设置了 arity 参数，可选交互式
    // @Option(names = {"-p", "--password"},  description = "Passphrase", interactive = true)
    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true)
    String password;

    // 设置了 arity 参数，可选交互式
    @Option(names = {"-cp", "--checkPassword"}, arity = "0..1", description = "Check Password", interactive = true)
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        // 1. 正常输入
        // new CommandLine(new Login()).execute("-u", "user123", "-p", "123", "-cp", "456");

        // 2.默认，是无法直接在命令中给交互选项指定任何参数的，只能通过交互式输入。但是比如命令中包含 -p xxx 就会报错。
        // new CommandLine(new Login()).execute("-u", "user123", "-p", "xxx", "-cp");

        // 3.可选交互式：在Option中，添加arity = "0..1"                  =>  0..1 表示参数个数，0个或1个
        // new CommandLine(new Login()).execute("-u", "user123", "-p", "xxx", "-cp");

        // 4.强制交互式
        // 如果用户不在命令中输入交互式选项（比如 -p），那么系统不会提示用户输入这个选项，属性的值将会设置默认值（比如null）
        /**
         * 输出如下：
         * password = null
         * checkPassword = null
         */
        // TODO: 由于有时候用户必输某个选项，而不能使用默认的空值。解决有2种方案：
        //  方案1=>在main中检测 args数组 是否存在对应选项，不存在就为数组增加选项元素
        //  方案2=>利用 反射 自动读取必填的选项名称
        new CommandLine(new Login()).execute("-u", "user123");
    }
}