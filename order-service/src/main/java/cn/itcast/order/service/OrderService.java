package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    public RestTemplate restTemplate;



    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        String URL = "http://localhost:8081/user/"+order.getUserId();
        User user = restTemplate.getForObject(URL, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }
}
