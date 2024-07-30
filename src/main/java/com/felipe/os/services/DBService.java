package com.felipe.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipe.os.domain.Cliente;
import com.felipe.os.domain.OS;
import com.felipe.os.domain.Tecnico;
import com.felipe.os.domain.enuns.Prioridade;
import com.felipe.os.domain.enuns.Status;
import com.felipe.os.repositories.ClienteRepository;
import com.felipe.os.repositories.OSRepository;
import com.felipe.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired //Autowired é uma anotação para realizar a injeção de dependências automaticamente em classes
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "571.728.730-50", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Felipe Goncalves", "212.761.390-20", "(88) 95555-5555");
		Cliente c1 = new Cliente(null, "Betina Campos", "760.129.590-79", "(88) 98888-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}
