package roteriza.mapper;

import org.springframework.stereotype.Component;

import roteriza.controller.dto.NodeDTO;
import roteriza.domain.Node;

@Component
public class NodeMapper {

	public NodeDTO toDTO(Node node) {
		NodeDTO dto = new NodeDTO();
		dto.setId(node.getId());
		dto.setNome(node.getNome());

		return dto;
	}

}