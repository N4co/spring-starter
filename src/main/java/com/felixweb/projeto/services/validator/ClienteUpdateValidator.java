package com.felixweb.projeto.services.validator;


import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.domain.enums.TipoCliente;
import com.felixweb.projeto.dto.ClienteDTO;
import com.felixweb.projeto.dto.ClienteNewDto;
import com.felixweb.projeto.repositories.ClienteRepository;
import com.felixweb.projeto.services.FieldMessage;
import com.felixweb.projeto.services.validator.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

                                                                    //pega o Map de variáveis de URI que estão na requisição
        Map<String, String> map = (Map<String, String> )request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        //a lógica abaixo diz que a uriId faz a comparação no update do email do cliente a garantia que não tenha um email já cadastrado em outro Id de um outro cliente


        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "email já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}