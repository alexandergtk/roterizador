package roteriza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import roteriza.domain.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {

	List<Node> findByNome(String nome);

}
