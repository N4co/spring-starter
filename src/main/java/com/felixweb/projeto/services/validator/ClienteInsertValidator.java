package com.felixweb.projeto.services.validator;


import com.felixweb.projeto.domain.Cliente;
import com.felixweb.projeto.domain.enums.TipoCliente;
import com.felixweb.projeto.dto.ClienteNewDto;
import com.felixweb.projeto.repositories.ClienteRepository;
import com.felixweb.projeto.services.FieldMessage;
import com.felixweb.projeto.services.validator.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();


        if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", " CPf Inválido"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", " CNPJ Inválido"));
        }

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null) {
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