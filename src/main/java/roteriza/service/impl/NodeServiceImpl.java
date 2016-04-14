package roteriza.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import roteriza.domain.Node;
import roteriza.repository.NodeRepository;
import roteriza.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

	@Autowired
	NodeRepository nodeRepository;

	@Override
	public Node save(Node node) {
		return nodeRepository.save(node);
	}

	@Override
	public List<Node> recuperaNode() {
		return nodeRepository.findAll();
	}

	@Override
	public Node findByName(String nome) {
		List<Node> nodes = nodeRepository.findByNome(nome);

		if (nodes.isEmpty()) {
			return null;
		} else {
			return nodes.get(0);
		}

	}
}
