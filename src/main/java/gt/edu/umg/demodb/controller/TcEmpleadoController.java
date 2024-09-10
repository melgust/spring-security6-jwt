package gt.edu.umg.demodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.model.TcEmpleado;
import gt.edu.umg.demodb.service.TcEmpleadoService;

@RestController
@RequestMapping("/empleado")
public class TcEmpleadoController {
	
	@Autowired
	TcEmpleadoService tcEmpleadoService;
	
	@GetMapping("/todos")
	public List<TcEmpleado> getAll() {
		return tcEmpleadoService.getAll();
	}
	
	@PostMapping("/agregar")
	public TcEmpleado save(@RequestBody TcEmpleado tcEmpleado) {
		return tcEmpleadoService.save(tcEmpleado);
	}

}
