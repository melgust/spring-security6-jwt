package gt.edu.umg.demodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.model.TcPuesto;
import gt.edu.umg.demodb.repository.TcPuestoRepository;

@Service
public class TcPuestoService {
	
	@Autowired
	TcPuestoRepository tcPuestoRepository;
	
	public List<TcPuesto> getAll() {
		List<TcPuesto> puestos = tcPuestoRepository.findAll();
		return puestos;
	}
	
	public TcPuesto getById(long puestoId) {
		Optional<TcPuesto> tcPuestoOptional = tcPuestoRepository.findById(puestoId);
		return tcPuestoOptional.get();
	}
	
	public TcPuesto save(TcPuesto tcPuesto) {
		tcPuesto = tcPuestoRepository.save(tcPuesto);
		return tcPuesto;
	}
	
	public void update(TcPuesto tcPuesto) {
		tcPuestoRepository.save(tcPuesto);
	}

}
