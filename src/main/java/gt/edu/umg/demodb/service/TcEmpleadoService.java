package gt.edu.umg.demodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.model.TcEmpleado;
import gt.edu.umg.demodb.repository.TcEmpleadoRepository;

@Service
public class TcEmpleadoService {
	
	@Autowired
	TcEmpleadoRepository tcEmpleadoRepository;
	
	public List<TcEmpleado> getAll() {
		List<TcEmpleado> empleados = tcEmpleadoRepository.findAll();
		return empleados;
	}
	
	public TcEmpleado getById(long empleadoId) {
		Optional<TcEmpleado> tcEmpleadoOptional = tcEmpleadoRepository.findById(empleadoId);
		return tcEmpleadoOptional.get();
	}
	
	public TcEmpleado save(TcEmpleado tcEmpleado) {
		tcEmpleado = tcEmpleadoRepository.save(tcEmpleado);
		return tcEmpleado;
	}
	
	public void update(TcEmpleado tcEmpleado) {
		tcEmpleadoRepository.save(tcEmpleado);
	}

}
