package com.ljh.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * 1.Picocli命令行框架入门 -> 1.命令解析
 *
 * （1）创建一个实现Runnable 或者 Callable 接口的类，这就是一个命令
 * （2）使用@Command 注解声明这个类是一个命令，name:指定命令的名称，version:指定命令的版本号，
 *     mixinStandardHelpOptions 属性设置为true，可以给应用程序自动添加 --help 和 --version 选项。
 * （3）使用@Option 注解声明“命令的选项”，names:指定命令的名称，description:指定命令的描述信息。
 * （4）使用@Parameters 注解声明“命令的参数”，paramLabel:指定命令的名称，defaultValue:指定命令的默认值。
 * （5）运行程序，输入 --help 选项，可以查看命令的名称、版本号、选项和参数的说明信息。
 * （6）Picocli 会将命令参数转换为强类型值，并自动注入到注解字段中。
 * （7）在类的 run 或者 call 方法中定义“业务逻辑”。当命令解析成功（用户敲了回车）后被调用。
 * （8）在 main 方法中，通过 CommandLine 对象的 execute 方法来处理用户输入的命令，剩下就交给 Picocli 框架来解析命令并执行业务逻辑。
 * （9）CommandLine.execute 方法返回一个退出代码。可以调用 System.exit(exitCode) 来退出程序,从而向调用进程表示成功 或 失败。
 */
@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    @Option(names = { "-s", "--font-size" }, description = "Font size")
    int fontSize = 19;

    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "Words to be translated into ASCII art.")
    private String[] words = { "Hello,", "picocli" };

    @Override
    public void run() {
        // 自己实现业务逻辑
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + String.join(",", words));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}