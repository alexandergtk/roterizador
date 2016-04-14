package roteriza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import roteriza.domain.Linha;

public interface LinhaRepository extends JpaRepository<Linha, Long> {

	List<Linha> findByNome(String nome);

}