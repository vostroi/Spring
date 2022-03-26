package start;

import com.vostroi.bean.Order;
import com.vostroi.service.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @title: Application
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1315:36
 */
public class CustomerApp {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("customer.xml");
        ctx.start();
        CustomerService customerService = ctx.getBean(CustomerService.class);
        List<Order> orderList = customerService.getOrderList("123");
        for (Order order : orderList){
            System.out.println(order);
        }

        System.in.read();
    }

}
