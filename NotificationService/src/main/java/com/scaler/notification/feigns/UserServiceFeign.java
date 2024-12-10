package com.scaler.notification.feigns;

import com.scaler.notification.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "products-service", url = "${fein.products.service.url}")
public interface UserServiceFeign {
    UserDto getUserDetails(Integer userId);
}
