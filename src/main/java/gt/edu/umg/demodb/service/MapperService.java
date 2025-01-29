package gt.edu.umg.demodb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.dto.TcEmpleadoDto;
import gt.edu.umg.demodb.dto.TcIdentificationDocumentDto;
import gt.edu.umg.demodb.dto.TcPuestoDto;
import gt.edu.umg.demodb.dto.TcRoleDto;
import gt.edu.umg.demodb.dto.TcUserDto;
import gt.edu.umg.demodb.model.TcEmpleado;
import gt.edu.umg.demodb.model.TcIdentificationDocument;
import gt.edu.umg.demodb.model.TcPuesto;
import gt.edu.umg.demodb.model.TcRole;
import gt.edu.umg.demodb.model.TcUser;

@Service
public class MapperService {

	@Autowired
	private ModelMapper modelMapper;

	private String date;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public Date getSubmissionDateConverted(String timezone) throws ParseException {
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		return dateFormat.parse(this.date);
	}

	public void setSubmissionDate(Date date, String timezone) {
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		this.date = dateFormat.format(date);
	}

	public TcIdentificationDocumentDto convertToTcIdentificationDocumentDto(
			TcIdentificationDocument tcIdentificationDocument) {
		TcIdentificationDocumentDto tcIdentificationDocumentDto = modelMapper.map(tcIdentificationDocument,
				TcIdentificationDocumentDto.class);
		return tcIdentificationDocumentDto;
	}

	public TcIdentificationDocument convertToTcIdentificationDocument(
			TcIdentificationDocumentDto tcIdentificationDocumentDto) throws ParseException {
		TcIdentificationDocument tcIdentificationDocument = modelMapper.map(tcIdentificationDocumentDto,
				TcIdentificationDocument.class);
		return tcIdentificationDocument;
	}

	public TcRoleDto convertToTcRoleDto(TcRole tcRole) {
		TcRoleDto tcRoleDto = modelMapper.map(tcRole, TcRoleDto.class);
		return tcRoleDto;
	}

	public List<TcRoleDto> convertToTcRolesDto(List<TcRole> tcRoles) {
		List<TcRoleDto> tcRolesDto = tcRoles.stream().map((tcRole) -> modelMapper.map(tcRole, TcRoleDto.class))
				.collect(Collectors.toList());
		return tcRolesDto;
	}

	public TcRole convertToTcRole(TcRoleDto tcRoleDto) throws ParseException {
		TcRole tcRole = modelMapper.map(tcRoleDto, TcRole.class);
		return tcRole;
	}

	public List<TcRole> convertToTcRoles(List<TcRoleDto> tcRolesDto) {
		List<TcRole> tcRoles = tcRolesDto.stream().map((tcRoleDto) -> modelMapper.map(tcRoleDto, TcRole.class))
				.collect(Collectors.toList());
		return tcRoles;
	}

	public TcUser convertToTcUser(TcUserDto tcUserDto) throws ParseException {
		TcUser tcUser = modelMapper.map(tcUserDto, TcUser.class);
		return tcUser;
	}

	public List<TcUser> convertToTcUsers(List<TcUserDto> tcUsersDto) {
		List<TcUser> tcUsers = tcUsersDto.stream().map((tcUserDto) -> modelMapper.map(tcUserDto, TcUser.class))
				.collect(Collectors.toList());
		return tcUsers;
	}

	public TcUserDto convertToTcUserDto(TcUser tcUser) {
		TcUserDto tcUserDto = modelMapper.map(tcUser, TcUserDto.class);
		return tcUserDto;
	}

	public List<TcUserDto> convertToTcUsersDto(List<TcUser> tcUsers) {
		List<TcUserDto> tcUsersDto = tcUsers.stream().map((tcUser) -> modelMapper.map(tcUser, TcUserDto.class))
				.collect(Collectors.toList());
		return tcUsersDto;
	}

	public TcPuesto convertToTcPuesto(TcPuestoDto tcPuestoDto) throws ParseException {
		TcPuesto tcPuesto = modelMapper.map(tcPuestoDto, TcPuesto.class);
		return tcPuesto;
	}

	public List<TcPuesto> convertToTcPuestos(List<TcPuestoDto> tcPuestosDto) {
		List<TcPuesto> tcPuestos = tcPuestosDto.stream()
				.map((tcPuestoDto) -> modelMapper.map(tcPuestoDto, TcPuesto.class)).collect(Collectors.toList());
		return tcPuestos;
	}

	public TcPuestoDto convertToTcPuestoDto(TcPuesto tcPuesto) {
		TcPuestoDto tcPuestoDto = modelMapper.map(tcPuesto, TcPuestoDto.class);
		return tcPuestoDto;
	}

	public List<TcPuestoDto> convertToTcPuestosDto(List<TcPuesto> tcPuestos) {
		List<TcPuestoDto> tcPuestosDto = tcPuestos.stream()
				.map((tcPuesto) -> modelMapper.map(tcPuesto, TcPuestoDto.class)).collect(Collectors.toList());
		return tcPuestosDto;
	}

	public TcEmpleado convertToTcEmpleado(TcEmpleadoDto tcEmpleadoDto) throws ParseException {
		TcEmpleado tcEmpleado = modelMapper.map(tcEmpleadoDto, TcEmpleado.class);
		return tcEmpleado;
	}

	public List<TcEmpleado> convertToTcEmpleados(List<TcEmpleadoDto> tcEmpleadosDto) {
		List<TcEmpleado> tcEmpleados = tcEmpleadosDto.stream()
				.map((tcEmpleadoDto) -> modelMapper.map(tcEmpleadoDto, TcEmpleado.class)).collect(Collectors.toList());
		return tcEmpleados;
	}

	public TcEmpleadoDto convertToTcEmpleadoDto(TcEmpleado tcEmpleado) {
		TcEmpleadoDto tcEmpleadoDto = modelMapper.map(tcEmpleado, TcEmpleadoDto.class);
		return tcEmpleadoDto;
	}

	public List<TcEmpleadoDto> convertToTcEmpleadosDto(List<TcEmpleado> tcEmpleados) {
		List<TcEmpleadoDto> tcEmpleadosDto = tcEmpleados.stream()
				.map((tcEmpleado) -> modelMapper.map(tcEmpleado, TcEmpleadoDto.class)).collect(Collectors.toList());
		return tcEmpleadosDto;
	}

}
