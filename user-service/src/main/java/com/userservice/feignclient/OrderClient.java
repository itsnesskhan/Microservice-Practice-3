package com.userservice.feignclient;

import com.userservice.dto.OrderDTO;
import com.userservice.payloads.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "Order-Client", url = "http://localhost:8082", path = "order-app/api")  without load balancing
@FeignClient(name = "order-service", path = "order-app/api")  //with load balancing
public interface OrderClient {


    @PostMapping
    ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDTO orderDTO);

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrder(@RequestBody OrderDTO orderDTO);
    @GetMapping("/order/{id}")
    public ResponseEntity<ApiResponse> getAllOrder(@PathVariable String id);

    @GetMapping("/order/user/{id}/feign")
    public ResponseEntity<ApiResponse> getAllOrderOfUserFeign(@PathVariable Integer id);

    @GetMapping("/order/user/{id}/rest")
    public ResponseEntity<ApiResponse> getAllOrderOfUserRestTemplate(@PathVariable Integer id);

}
