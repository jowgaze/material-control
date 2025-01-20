package io.github.materialcontrol.ms_loan.services.client;

import io.github.materialcontrol.ms_loan.services.client.dtos.ItemResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "ms-materials", path = "/api/v1/items")
public interface ItemClient {

    @GetMapping("/{id}")
    ItemResponseDto findById(@PathVariable("id") Long id);

    @PutMapping("/{id}/status")
    ItemResponseDto updateStatus(@PathVariable("id") Long id);
}
