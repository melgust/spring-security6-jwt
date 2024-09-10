package gt.edu.umg.demodb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.dto.TcIdentificationDocumentDto;
import gt.edu.umg.demodb.dto.TcRoleDto;
import gt.edu.umg.demodb.model.TcIdentificationDocument;
import gt.edu.umg.demodb.model.TcRole;

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

	public TcRoleDto convertToTcRoleDto(TcRole TcRole) {
		TcRoleDto TcRoleDto = modelMapper.map(TcRole, TcRoleDto.class);
		return TcRoleDto;
	}

	public TcRole convertToTcRole(TcRoleDto TcRoleDto) throws ParseException {
		TcRole TcRole = modelMapper.map(TcRoleDto, TcRole.class);
		return TcRole;
	}

}
