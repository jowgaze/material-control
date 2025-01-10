package io.github.materialcontrol.ms_materials;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/materials")
public class TestController {

    @GetMapping
    public String test(){
        return "ms-materials Ok";
    }
}
