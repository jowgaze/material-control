package io.github.materialcontrol.ms_loan.services.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemResponseDto {
    private Long id;
    private String code;
    private String status;
    private MaterialResponseDto material;
}
