package com.example.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Depp
 */
@FeignClient("storage-service")
public interface StorageFeignClient {
    @GetMapping("storage/deduct")
    boolean deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
