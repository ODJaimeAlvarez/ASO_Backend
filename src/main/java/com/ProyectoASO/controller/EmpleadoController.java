package com.ProyectoASO.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoASO.dto.ClienteDTO;
import com.ProyectoASO.dto.ClienteNuevoDTO;
import com.ProyectoASO.dto.EmpleadoDTO;
import com.ProyectoASO.dto.EmpleadoNuevoDTO;
import com.ProyectoASO.responses.MethodResponse;
import com.ProyectoASO.service.IEmpleadoService;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {
	private IEmpleadoService empleadoService;
	
	public EmpleadoController(IEmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	@GetMapping
	public ResponseEntity<List<EmpleadoDTO>> getAll(){
		return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoDTO> update(@PathVariable Integer id,@RequestBody EmpleadoDTO empleado){
		return new ResponseEntity<>(empleadoService.update(id, empleado),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoDTO>getById(@PathVariable Integer id){
		return new ResponseEntity<>(empleadoService.getById(id), HttpStatus.OK);
	}
	@PutMapping("/alta/{id}")
	public ResponseEntity<MethodResponse> activate(@PathVariable Integer id){
		return empleadoService.activate(id);
	}

	@PutMapping("/baja/{id}")
	public ResponseEntity<MethodResponse>deActivate(@PathVariable Integer id){
		return empleadoService.deActivate(id);
	}
	@PostMapping("/register")
	public ResponseEntity<EmpleadoDTO> register(@RequestBody EmpleadoNuevoDTO newEmp){
		return new ResponseEntity<>(empleadoService.save(newEmp),HttpStatus.OK);
	}
}
