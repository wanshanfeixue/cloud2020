package com.edian.springcloud.controller;

import com.edian.springcloud.entities.CommonResult;
import com.edian.springcloud.entities.Payment;
import com.edian.springcloud.loadbalancer.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Description: OrderController 类（或接口）是 订单操作
 * @Author: wu
 * @Date: 2020/4/16 12:22
 */
@RestController
@Slf4j
public class OrderController {
    //单机版
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //负载均衡版
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> creat(Payment payment) {
        //postForObject返回对象为响应体中数据转换成的对象，基本上可以理解为json
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment) {
        //返回对象为ResponseEntity对象，包含了响应中的一些重要信息，比如响应头、状态码、响应体等
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult(444, "操作失败");
        }
    }

    @GetMapping("/consumer/payment/getBalancer")
    public String getPaymentBalancer() {
        //获取payment集群的全部服务
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        //自己手写的负载均衡方法，获取某一个服务
        ServiceInstance instance = loadBalancer.instances(instances);
        //获取服务的地址
        URI uri = instance.getUri();
        //远程调用peyment服务
        return restTemplate.getForObject(uri+"/payment/getBalancer",String.class);
    }
}
