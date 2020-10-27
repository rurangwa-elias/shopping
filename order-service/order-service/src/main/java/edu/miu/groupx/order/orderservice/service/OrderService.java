package edu.miu.groupx.order.orderservice.service;


import edu.miu.groupx.order.orderservice.Repository.OrderRepository;
import edu.miu.groupx.order.orderservice.domain.OrderDetails;
import edu.miu.groupx.order.orderservice.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }

    public List<Orders> getByUserId(long userId){
        List<Orders> result = orderRepository.findAll().stream().filter(a -> a.getUserId().equals(userId)).collect(Collectors.toList());

        return result;
    }

    public Orders addOrder(Orders order){
        return orderRepository.save(order);
    }

    public Optional<Orders> getByorderId(long orderId){
        return orderRepository.findById(orderId);
    }

    public void deleteOrderById(long orderId){
       orderRepository.deleteById(orderId);
    }

    public Orders updateOrder(long orderId , Orders order){
        Optional<Orders> fetchedProduct = orderRepository.findById(orderId);

        if(!fetchedProduct.isPresent()){
            return null;
        }

        order.setOrderId(orderId);
        
        List<OrderDetails> orderDetails = order.getOrderDetails();

        Double total = 0.0;
        for(OrderDetails o : orderDetails){
        	o.setOrder(order);
            o.setSubTotal(o.getQuantity() * o.getItemPrice());
            total =+ o.getSubTotal();
        }

        order.setOrderDetails(orderDetails);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

}
