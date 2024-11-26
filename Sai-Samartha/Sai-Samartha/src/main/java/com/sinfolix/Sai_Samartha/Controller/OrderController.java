package com.sinfolix.Sai_Samartha.Controller;


import com.sinfolix.Sai_Samartha.DTO.OrderDTO;
import com.sinfolix.Sai_Samartha.DTO.OrderProductDTO;
import com.sinfolix.Sai_Samartha.DTO.OrderRequestDTO;
import com.sinfolix.Sai_Samartha.DTO.UserDTO;
import com.sinfolix.Sai_Samartha.ENUM.StatusEnum;
import com.sinfolix.Sai_Samartha.Entities.Order;
import com.sinfolix.Sai_Samartha.Service.OrderService;
import com.sinfolix.Sai_Samartha.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "/**")
// API Endpoint for Orders
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        OrderDTO order = new OrderDTO();
        // map orderRequestDTO to Order
        order.setCustomerAddress(orderRequestDTO.getCustomerAddress());
        order.setCustomerEmail(orderRequestDTO.getCustomerEmail());
        order.setCustomerName(orderRequestDTO.getCustomerName());
        order.setOrderDate(LocalDate.now());
        order.setStatus(StatusEnum.ORDER_PLACED.getStatus());
        order.setModified_time(LocalDate.now());

        List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderRequestDTO.getOrderProductDTOList()) {
            OrderProductDTO newOrderProductDTO = new OrderProductDTO();
            newOrderProductDTO.setProductCatalogueDTO(orderProductDTO.getProductCatalogueDTO());
            newOrderProductDTO.setQuantity(orderProductDTO.getQuantity());
            orderProductDTOList.add(newOrderProductDTO);
        }
        OrderDTO saved = orderService.createOrder(order, orderProductDTOList, username);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);


    }

    @GetMapping("/{email}")
    public List<Order> getOrderListByCustomerEmail(@PathVariable String email) {
        return orderService.getOrderListByCustomerEmail(email);
    }


    @GetMapping("/orders")
    public List<Order> getUserOrders(@PathVariable Long userId,@AuthenticationPrincipal UserDetails userDetails) {
        UserDTO user = userService.findByUsername(userDetails.getUsername());



        return orderService.findAllByUsername(user.getUsername());
    }
}

