package com.edian.springcloud.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer implements LoadBalancer {
    //原子整型,初始化为0
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //先获取在增加，rest接口调用的次数
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next)); //CAS比较期望值
        System.out.println("******第几次访问，次数是：" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //获取服务实例脚标
        //负载均衡算法：rest接口第几次请求数 % 服务器集群总数 = 实际调用服务器位置下标，每次服务器重启后rest接口计数从1开始。
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
