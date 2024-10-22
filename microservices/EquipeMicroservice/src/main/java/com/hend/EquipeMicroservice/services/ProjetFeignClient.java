package com.hend.EquipeMicroservice.services;

import com.hend.EquipeMicroservice.entities.ProjetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProjetMicroservice", url = "${application.config.projet-url}", configuration = FeignConfig.class)
public interface ProjetFeignClient {

    @GetMapping("/{idProjet}")
    ProjetResponse getProjetById(@PathVariable("idProjet") String projetId);

}
