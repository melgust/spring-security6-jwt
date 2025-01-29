package gt.edu.umg.demodb.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.dto.TcEmpleadoDto;
import gt.edu.umg.demodb.model.TcEmpleado;
import gt.edu.umg.demodb.repository.TcEmpleadoRepository;

@Service
public class TcEmpleadoService {
	
	@Autowired
	TcEmpleadoRepository tcEmpleadoRepository;
	
	@Autowired
	MapperService mapperService;
	
	public ResponseEntity<?> getAll() {
		List<TcEmpleado> empleados = tcEmpleadoRepository.findAll();
		return new ResponseEntity<List<?>>(mapperService.convertToTcEmpleadosDto(empleados), HttpStatus.OK);
	}
	
	public TcEmpleadoDto getById(long empleadoId) {
		Optional<TcEmpleado> tcEmpleadoOptional = tcEmpleadoRepository.findById(empleadoId);
		return mapperService.convertToTcEmpleadoDto(tcEmpleadoOptional.get());
	}
	
	public ResponseEntity<?> save(TcEmpleadoDto tcEmpleadoDto) throws ParseException {
		TcEmpleado tcEmpleado = mapperService.convertToTcEmpleado(tcEmpleadoDto);
		tcEmpleado = tcEmpleadoRepository.save(tcEmpleado);
		return new ResponseEntity<TcEmpleadoDto>(mapperService.convertToTcEmpleadoDto(tcEmpleado), HttpStatus.OK);
	}
	
	public void update(TcEmpleadoDto tcEmpleadoDto) throws ParseException {
		TcEmpleado tcEmpleado = mapperService.convertToTcEmpleado(tcEmpleadoDto);
		tcEmpleadoRepository.save(tcEmpleado);
	}

}
