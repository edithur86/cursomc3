package com.ediads.cursomc3.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ediads.cursomc3.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByEmail(String email);
  
  

}
