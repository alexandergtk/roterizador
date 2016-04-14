package roteriza.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roteriza.domain.Linha;
import roteriza.domain.Node;
import roteriza.domain.Rota;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrajetoMenor {

	private List<Node> nodes;
	private List<Rota> rotas;
	private Set<Node> pNodes;
	private Set<Node> aNodes;
	private Map<Node, Node> anteriores;
	private Map<Node, BigDecimal> distancia;
	private BigDecimal totalDistancia;

	public TrajetoMenor(Linha linha) {
		this.nodes = linha.getNodes();
		this.rotas = linha.getRotas();
	}

	public void execute(Node origem) {
		pNodes = new HashSet<Node>();
		aNodes = new HashSet<Node>();
		aNodes.add(origem);
		anteriores = new HashMap<Node, Node>();
		distancia = new HashMap<Node, BigDecimal>();
		distancia.put(origem, BigDecimal.ZERO);
		totalDistancia = new BigDecimal(0);

		while (aNodes.size() > 0) {
			Node node = getMenorDistancia(aNodes);
			pNodes.add(node);
			aNodes.remove(node);
			buscaCaminhoRapido(node);
		}
	}

	private Node getMenorDistancia(Set<Node> nodes) {
		Node menor = null;
		for (Node node : nodes) {
			if (menor == null) {
				menor = node;
			} else {
				if (getDistanciaCurta(menor).compareTo(getDistanciaCurta(node)) == 1) {
					menor = node;
				}
			}
		}
		return menor;
	}

	private void buscaCaminhoRapido(Node node) {
		List<Node> proximoNo = getPercusos(node);
		for (Node target : proximoNo) {
			if (getDistanciaCurta(target).compareTo(getDistanciaCurta(node).add(getDistancia(node, target))) == 1) {
				distancia.put(target, getDistanciaCurta(node).add(getDistancia(node, target)));
				anteriores.put(target, node);
				aNodes.add(target);
			}
		}
	}

	private BigDecimal getDistancia(Node node, Node target) {
		for (Rota rota : rotas) {
			if (rota.getOrigem().equals(node) && rota.getDestino().equals(target)) {
				return rota.getDistancia();
			}
		}
		throw new RuntimeException("Erro");
	}

	private List<Node> getPercusos(Node node) {
		List<Node> percursos = new ArrayList<Node>();
		for (Rota rota : rotas) {
			if (rota.getOrigem().equals(node) && !Caminho(rota.getDestino())) {
				percursos.add(rota.getDestino());
			}
		}
		return percursos;
	}

	private boolean Caminho(Node node) {
		return pNodes.contains(node);
	}

	private BigDecimal getDistanciaCurta(Node destination) {
		BigDecimal d = distancia.get(destination);
		if (d == null) {
			return BigDecimal.valueOf(Integer.MAX_VALUE);
		} else {
			return d;
		}
	}

	public LinkedList<Node> getCaminho(Node pontoCaminho) {
		Node pultimo = pontoCaminho;
		LinkedList<Node> caminho = new LinkedList<Node>();
		Node percorre = pontoCaminho;

		if (anteriores.get(percorre) == null) {
			return null;
		}
		caminho.add(percorre);
		while (anteriores.get(percorre) != null) {
			percorre = anteriores.get(percorre);
			caminho.add(percorre);
			totalDistancia = totalDistancia.add(getDistancia(percorre, pultimo));
			pultimo = percorre;
		}

		Collections.reverse(caminho);
		return caminho;
	}

}