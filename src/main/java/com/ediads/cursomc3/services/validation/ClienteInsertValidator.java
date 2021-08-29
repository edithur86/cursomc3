package com.ediads.cursomc3.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ediads.cursomc3.domain.Cliente;
import com.ediads.cursomc3.domain.enums.TipoCliente;
import com.ediads.cursomc3.dto.ClienteNewDTO;
import com.ediads.cursomc3.repositories.ClienteRepository;
import com.ediads.cursomc3.resouces.exception.FieldMessage;
import com.ediads.cursomc3.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfouCnpj())){
			list.add(new FieldMessage("cpfouCnpj", "CPF invalido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfouCnpj())){
			list.add(new FieldMessage("cpfouCnpj", "CNPJ invalido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux!= null) {
			list.add(new FieldMessage("email" , "Email Ja existente"));
			
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		}
	}

		
