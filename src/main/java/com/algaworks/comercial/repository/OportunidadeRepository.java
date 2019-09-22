package com.algaworks.comercial.repository;

import com.algaworks.comercial.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

	@Transactional(readOnly = true)
	Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
}
