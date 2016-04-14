package roteriza.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roteriza.domain.Node;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeDTO {

	private Long id;
	private String nome;

	public Node toEntity() {
		Node node = new Node();
		node.setId(id);
		return node;
	}

}