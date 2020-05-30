package com.edian.springcloud.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import java.util.List;

public interface LoadBalancer {
    //负载均衡，获取服务实例
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
