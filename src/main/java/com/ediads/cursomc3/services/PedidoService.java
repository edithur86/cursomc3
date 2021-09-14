package com.ediads.cursomc3.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ediads.cursomc3.domain.ItenPedido;
import com.ediads.cursomc3.domain.PagamentoComBoleto;
import com.ediads.cursomc3.domain.Pedido;
import com.ediads.cursomc3.domain.enums.EstadoPagamento;
import com.ediads.cursomc3.repositories.ItenPedidoRepository;
import com.ediads.cursomc3.repositories.PagamentoRepository;
import com.ediads.cursomc3.repositories.PedidoRepository;
import com.ediads.cursomc3.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItenPedidoRepository itenPedidoRepository;
	
	@Autowired
	private ClienteService ClienteService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()));
		


		 

		
	}
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date() );
		obj.setCliente(ClienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItenPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itenPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
		
	}

}
