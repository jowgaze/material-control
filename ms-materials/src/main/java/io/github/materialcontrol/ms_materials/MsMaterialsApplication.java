package io.github.materialcontrol.ms_materials;

import io.github.materialcontrol.ms_materials.entities.item.Item;
import io.github.materialcontrol.ms_materials.entities.material.Material;
import io.github.materialcontrol.ms_materials.repositories.ItemRepository;
import io.github.materialcontrol.ms_materials.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MsMaterialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMaterialsApplication.class, args);
	}

}
