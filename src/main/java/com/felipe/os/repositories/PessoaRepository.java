package com.felipe.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.felipe.os.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Query("SELECT obj FROM TB_PESSOA obj WHERE obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
	
}
