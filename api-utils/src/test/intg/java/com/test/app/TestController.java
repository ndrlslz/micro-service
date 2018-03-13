package com.test.app;

import com.test.constraint.ValidShopId;
import com.test.log.PerformanceLog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    @PerformanceLog
    public String testEndpoint(@RequestParam @ValidShopId String shopId) {
        System.out.println(shopId);
        return "test";
    }
}
