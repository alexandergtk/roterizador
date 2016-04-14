package roteriza.service;

import java.util.List;

import roteriza.domain.Node;

public interface NodeService {

	Node save(Node node);

	List<Node> recuperaNode();

	Node findByName(String nome);
}