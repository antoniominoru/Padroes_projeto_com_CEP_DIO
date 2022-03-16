package one.digitalinnovation.lab_padroes_projeto_spring.service;

import one.digitalinnovation.lab_padroes_projeto_spring.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @RequestMapping(method = GET, value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
