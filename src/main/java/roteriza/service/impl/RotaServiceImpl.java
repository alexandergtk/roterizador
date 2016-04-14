package roteriza.service.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roteriza.controller.dto.NodeDTO;
import roteriza.controller.dto.RotaDTO;
import roteriza.controller.dto.RoterizaRotaDTO;
import roteriza.domain.Linha;
import roteriza.domain.Node;
import roteriza.domain.Rota;
import roteriza.exception.NotFoundException;
import roteriza.mapper.NodeMapper;
import roteriza.mapper.RotaMapper;
import roteriza.repository.RotaRepository;
import roteriza.service.LinhaService;
import roteriza.service.NodeService;
import roteriza.service.RotaService;
import roteriza.utils.TrajetoMenor;

@Service
public class RotaServiceImpl implements RotaService {

	@Autowired
	RotaRepository rotaRepository;

	@Autowired
	LinhaService linhaService;

	@Autowired
	NodeMapper nodeMapper;

	@Autowired
	NodeService nodeService;

	@Autowired
	RotaMapper rotaMapper;

	@Override
	public Rota save(final Rota input) {
		input.setOrigem(addNode(input.getOrigem().getNome(), input.getLinha().getNome()));
		input.setDestino(addNode(input.getDestino().getNome(), input.getLinha().getNome()));
		input.setLinha(addLinha(input.getLinha().getNome()));

		Rota rota = rotaRepository.save(input);

		return rota;
	}

	// @Override
	// public Rota update(final Rota input) {
	// input.setOrigem(addNode(input.getOrigem().getNome(),
	// input.getLinha().getNome()));
	// input.setDestino(addNode(input.getDestino().getNome(),
	// input.getLinha().getNome()));
	// input.setLinha(addLinha(input.getLinha().getNome()));
	//
	// Rota rota = rotaRepository.save(input);
	//
	// return rota;
	// }

	private Node addNode(String nodeNome, String linhaNome) {
		Node node = nodeService.findByName(nodeNome);

		if (node == null) {
			node = new Node();
			node.setNome(nodeNome);
			node.setLinha(addLinha(linhaNome));

			return nodeService.save(node);
		} else {
			return node;
		}
	}

	private Linha addLinha(String linhaNome) {
		Linha linha = linhaService.findByName(linhaNome);

		if (linha == null) {
			return linhaService.save(new Linha(linhaNome));
		} else {
			return linha;
		}
	}

	@Override
	public List<Rota> findAll() {
		return rotaRepository.findAll();
	}

	@Override
	public void deleteRota(Long id) {
		Rota entity = getById(id);

		if (entity == null) {
			throw new NotFoundException(Rota.class);
		}

		rotaRepository.delete(entity);
	}

	private RoterizaRotaDTO montaRota(LinkedList<Node> rota, BigDecimal distancia, BigDecimal autonomia,
			BigDecimal litroValor) {

		if (rota == null) {
			return new RoterizaRotaDTO();
		} else {
			LinkedList<NodeDTO> dto = new LinkedList<NodeDTO>();
			for (Node node : rota) {
				dto.add(nodeMapper.toDTO(node));
			}

			BigDecimal cost = distancia.divide(autonomia).multiply(litroValor);

			return new RoterizaRotaDTO(dto, cost);
		}
	}

	@Override
	public RoterizaRotaDTO calculaRota(Linha linha, Node origem, Node destino, BigDecimal litroValor,
			BigDecimal autonomia) {

		TrajetoMenor tm = new TrajetoMenor(linha);
		tm.execute(origem);

		return montaRota(tm.getCaminho(destino), tm.getTotalDistancia(), autonomia, litroValor);
	}

	@Override
	public RoterizaRotaDTO calculaRota(String pLinhaNome, String pOrigem, String pDestino, BigDecimal litroValor,
			BigDecimal autonomia) {

		Node origem = nodeService.findByName(pOrigem);
		Node destino = nodeService.findByName(pDestino);
		Linha linha = linhaService.findByName(pLinhaNome);

		if (linha == null || origem == null || destino == null) {
			return new RoterizaRotaDTO(null, null);
		}

		if (destino.equals(origem)) {
			LinkedList<NodeDTO> nodes = new LinkedList<NodeDTO>();
			nodes.add(nodeMapper.toDTO(origem));

			return new RoterizaRotaDTO(nodes, BigDecimal.ZERO);
		}

		return calculaRota(linha, origem, destino, litroValor, autonomia);
	}

	@Override
	public Rota update(Long id, RotaDTO input) {
		Rota entity = getById(id);

		entity.setLinha(input.getLinha().toEntity());
		entity.setOrigem(input.getOrigem().toEntity());
		entity.setDestino(input.getDestino().toEntity());
		entity.setDistancia(input.getDistancia());
		entity = rotaRepository.save(entity);

		return entity;
	}

	@Override
	public Rota getById(Long id) {
		Rota entity = rotaRepository.findOne(id);

		if (entity == null) {
			throw new NotFoundException(Rota.class);
		}
		return entity;
	}

}