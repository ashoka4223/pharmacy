package com.sinfolix.Sai_Samartha.Service;

import com.sinfolix.Sai_Samartha.DTO.OrderDTO;
import com.sinfolix.Sai_Samartha.DTO.OrderProductDTO;
import com.sinfolix.Sai_Samartha.DTO.UserDTO;
import com.sinfolix.Sai_Samartha.Entities.Order;
import com.sinfolix.Sai_Samartha.Entities.OrderProduct;
import com.sinfolix.Sai_Samartha.Entities.User;
import com.sinfolix.Sai_Samartha.Exceptions.ResourceNotFoundException;
import com.sinfolix.Sai_Samartha.Repository.OrderProductRepository;
import com.sinfolix.Sai_Samartha.Repository.OrderRepository;
import com.sinfolix.Sai_Samartha.Repository.ProductCatalogueRepo;
import com.sinfolix.Sai_Samartha.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductCatalogueRepo productCatalogueRepo;
    private final OrderProductRepository orderProductRepository;
    @Autowired
    private UserService userService;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductCatalogueRepo productCatalogueRepo, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productCatalogueRepo = productCatalogueRepo;
        this.orderProductRepository = orderProductRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO, List<OrderProductDTO> orderProductDTOList, String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerEmail(orderDTO.getCustomerEmail());
        order.setCustomerAddress(orderDTO.getCustomerAddress());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setModified_time(orderDTO.getModified_time());
        order.setStatus(orderDTO.getStatus());
        order.setPrescriptionBased(orderDTO.isPrescriptionBased());
        order.setReviewed((orderDTO.isReviewed()));


        Order savedOrder = orderRepository.save(order);



        for (OrderProductDTO orderProductDTO : orderProductDTOList) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(savedOrder);
            orderProduct.setProductCatalogue(productCatalogueRepo.findById((long) orderProductDTO.getProductCatalogueDTO().getId()).orElseThrow(() -> new ResourceNotFoundException("Product Catalogue", "ID ", orderProductDTO.getProductCatalogueDTO().getId())));
            orderProduct.setQuantity(orderProductDTO.getQuantity());
            orderProductRepository.save(orderProduct);
            user.getOrders().add(orderProduct);
            UserDTO userDTO = userService.userToUserDTO(user);
            userService.saveNewUser(userDTO);
        }

        return OrderToOrderDTO(savedOrder);
    }



    // Return all the product ordered by specific customer

    public List<Order> getOrderListByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }

    // Return order by ID

    public OrderDTO getOrderById(Long orderId) {
        return null;
    }

    //  Return all the placed orders
    public List<OrderDTO> getAllOrders() {
        List<Order> order = orderRepository.findAll();
        return order.stream().map(e -> this.OrderToOrderDTO(e)).collect(Collectors.toList());
    }

    private OrderDTO OrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setCustomerEmail(order.getCustomerEmail());
        orderDTO.setCustomerAddress(order.getCustomerAddress());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setModified_time(order.getModified_time());
        orderDTO.setStatus(order.getStatus());
        return orderDTO;

    }

    public List<Order> findAllByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getOrders() == null || user.getOrders().isEmpty()) {
            return Collections.emptyList();
        }
        return userRepository.findAllByUsername(username);
    }
}

