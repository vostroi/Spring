package start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Administrator
 * @title: Application
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1316:45
 */
public class OrderApp {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("order.xml");
        ctx.start();
        System.in.read();
    }

}
