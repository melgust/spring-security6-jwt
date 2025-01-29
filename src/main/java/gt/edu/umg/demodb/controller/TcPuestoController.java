package gt.edu.umg.demodb.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.dto.TcPuestoDto;
import gt.edu.umg.demodb.service.ErrorManagerService;
import gt.edu.umg.demodb.service.TcPuestoService;

@RestController
@RequestMapping("/puesto")
public class TcPuestoController {
	
	@Autowired
	TcPuestoService tcPuestoService;
	
	@Autowired
	ErrorManagerService errorManagerService;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAll() {
		return tcPuestoService.getAll();
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<?> save(@RequestBody TcPuestoDto tcPuestoDto) {
		try {
			return tcPuestoService.save(tcPuestoDto);
		} catch (ParseException e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
