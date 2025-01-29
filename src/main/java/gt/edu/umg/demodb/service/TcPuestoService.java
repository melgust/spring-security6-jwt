package gt.edu.umg.demodb.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.dto.TcPuestoDto;
import gt.edu.umg.demodb.model.TcPuesto;
import gt.edu.umg.demodb.repository.TcPuestoRepository;

@Service
public class TcPuestoService {

	@Autowired
	TcPuestoRepository tcPuestoRepository;

	@Autowired
	MapperService mapperService;

	public ResponseEntity<?> getAll() {
		List<TcPuesto> puestos = tcPuestoRepository.findAll();
		return new ResponseEntity<List<?>>(mapperService.convertToTcPuestosDto(puestos), HttpStatus.OK);
	}

	public TcPuestoDto getById(long puestoId) {
		Optional<TcPuesto> tcPuestoOptional = tcPuestoRepository.findById(puestoId);
		return mapperService.convertToTcPuestoDto(tcPuestoOptional.get());
	}

	public ResponseEntity<?> save(TcPuestoDto tcPuestoDto) throws ParseException {
		TcPuesto tcPuesto = mapperService.convertToTcPuesto(tcPuestoDto);
		tcPuesto = tcPuestoRepository.save(tcPuesto);
		return new ResponseEntity<TcPuestoDto>(mapperService.convertToTcPuestoDto(tcPuesto), HttpStatus.OK);
	}

	public void update(TcPuestoDto tcPuestoDto) throws ParseException {
		TcPuesto tcPuesto = mapperService.convertToTcPuesto(tcPuestoDto);
		tcPuestoRepository.save(tcPuesto);
	}

}
