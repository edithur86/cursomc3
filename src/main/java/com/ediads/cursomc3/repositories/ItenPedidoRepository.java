package com.ediads.cursomc3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ediads.cursomc3.domain.ItenPedido;

@Repository
public interface ItenPedidoRepository extends JpaRepository<ItenPedido, Integer>{

}
