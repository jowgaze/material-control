package io.github.materialcontrol.ms_loan.services.client;

import io.github.materialcontrol.ms_loan.services.client.dtos.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-users", path = "/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}")
    UserResponseDto findById(@PathVariable("id") Long id);
}