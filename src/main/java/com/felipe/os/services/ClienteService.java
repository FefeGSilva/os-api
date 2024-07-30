package com.felipe.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.os.domain.Pessoa;
import com.felipe.os.domain.Cliente;
import com.felipe.os.dtos.ClienteDTO;
import com.felipe.os.repositories.PessoaRepository;
import com.felipe.os.repositories.ClienteRepository;
import com.felipe.os.services.exceptions.DataIntergratyViolationException;
import com.felipe.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	// Busca um cliente pelo ID
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encotrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// MÉTODO CRUD (CREATE, UPDATE && DELETE)
	
	// Cria um cliente
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntergratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	// Atualiza um cliente pelo ID
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntergratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());
		return repository.save(oldObj);
	}

	// Deleta um cliente pelo ID
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntergratyViolationException("Pessoa possui Ordens de Serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	//Busca um cliente pelo CPF
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
