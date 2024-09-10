package gt.edu.umg.demodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.model.TcPuesto;
import gt.edu.umg.demodb.service.TcPuestoService;

@RestController
@RequestMapping("/puesto")
public class TcPuestoController {
	
	@Autowired
	TcPuestoService tcPuestoService;
	
	@GetMapping("/todos")
	public List<TcPuesto> getAll() {
		return tcPuestoService.getAll();
	}
	
	@PostMapping("/agregar")
	public TcPuesto save(@RequestBody TcPuesto tcPuesto) {
		return tcPuestoService.save(tcPuesto);
	}

}
