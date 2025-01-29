package gt.edu.umg.demodb.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.umg.demodb.dto.TcUserDto;
import gt.edu.umg.demodb.jwt.JwtProvider;
import gt.edu.umg.demodb.jwt.User;
import gt.edu.umg.demodb.model.TcUser;
import gt.edu.umg.demodb.repository.TcMenuRoleRepository;
import gt.edu.umg.demodb.repository.TcUserRepository;
import gt.edu.umg.demodb.utils.ApiResponse;
import gt.edu.umg.demodb.utils.KeyDictionary;
import gt.edu.umg.demodb.utils.ObjParameter;
import gt.edu.umg.demodb.utils.RegisterStatus;
import gt.edu.umg.demodb.utils.ResponseResult;

@Service
public class TcUserService {

	@Autowired
	TcUserRepository tcUserRepository;

	@Autowired
	ErrorManagerService errorManagerService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TcMenuRoleRepository tcMenuRoleRepository;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	EncryptService encryptService;

	@Autowired
	MailService mailService;

	@Autowired
	PasswordGeneratorService passwordGeneratorService;

	@Autowired
	MapperService mapperService;

	@Transactional(rollbackFor = { Exception.class })
	public ResponseEntity<?> setUser(TcUserDto tcUserDto, long userTokenId) throws Exception {
		tcUserDto.setEmail(tcUserDto.getEmail().trim().toLowerCase());
		tcUserDto.setUsername(tcUserDto.getUsername().trim().toLowerCase());
		Optional<TcUser> tcUserOptional = tcUserRepository.findByUsername(tcUserDto.getUsername());
		if (!tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("El usuario ya existe, favor de verificar", HttpStatus.BAD_REQUEST);
		}
		tcUserOptional = tcUserRepository.findByEmail(tcUserDto.getEmail());
		if (!tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("El correo electrónico ya se encuentra registrado",
					HttpStatus.BAD_REQUEST);
		}
		tcUserOptional = tcUserRepository.findByDocumentNumber(tcUserDto.getDocumentNumber());
		if (!tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("El número de documento ya se encuentra registrado",
					HttpStatus.BAD_REQUEST);
		}
		String password = passwordGeneratorService.generatePassword(8, KeyDictionary.STRONG);
		tcUserDto.setPassword(passwordEncoder.encode(password));
		tcUserDto.setStatusId(RegisterStatus.active.getValue());
		TcUser tcUser = mapperService.convertToTcUser(tcUserDto);
		tcUser = tcUserRepository.save(tcUser);
		List<ObjParameter> parameters = new ArrayList<>();
		ObjParameter objParameter = new ObjParameter();
		objParameter.setField("username");
		objParameter.setValue(tcUser.getUsername());
		parameters.add(objParameter);
		objParameter = new ObjParameter();
		objParameter.setField("message");
		objParameter.setValue("Hola " + tcUser.getFullname()
				+ ", has sido registrado al sistema Demo UMG. Tus credenciales son las siguientes:");
		parameters.add(objParameter);
		objParameter = new ObjParameter();
		objParameter.setField("url");
		objParameter.setValue(encryptService.urlSite + "/#/authentication/login");
		parameters.add(objParameter);
		try {
			mailService.sendEmail(tcUser.getEmail(), "mcalic1@miumg.edu.gt", "register", parameters);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(ResponseResult.success.getValue(),
							"Usuario creado exitosamente, confirme su solicitud a través del correo enviado", password),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(ResponseResult.success.getValue(),
					"No fue posible enviar el correo de notificación, comuníquese con el administrador del sistema",
					password), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> updateUser(Long userTokenId, Long userId, TcUserDto tcUserDtoIn) throws ParseException {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("Registro no encontrado", HttpStatus.BAD_REQUEST);
		}
		TcUser tcUser = tcUserOptional.get();
		tcUser.setUpdatedBy(userTokenId);
		tcUser.setUpdatedAt(new Date());
		if (tcUserDtoIn.getPhoto() != null) {
			tcUser.setPhoto(tcUserDtoIn.getPhoto());
		}
		tcUser.setEmail(tcUserDtoIn.getEmail());
		tcUser.setBirthday(tcUserDtoIn.getBirthday());
		tcUser.setDocumentNumber(tcUser.getDocumentNumber());
		tcUser.setFullname(tcUserDtoIn.getFullname());
		tcUser.setPhone(tcUserDtoIn.getPhone());
		if (tcUserDtoIn.getTcRole() != null) {
			tcUser.setTcRole(mapperService.convertToTcRole(tcUserDtoIn.getTcRole()));
		}
		tcUserRepository.save(tcUser);
		return new ResponseEntity<String>(ResponseResult.success.getMessage(), HttpStatus.OK);
	}

	public ResponseEntity<?> getUserById(Long userId) {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("Registro no encontrado", HttpStatus.BAD_REQUEST);
		}
		TcUser tcUser = tcUserOptional.get();
		TcUserDto tcUserDto = mapperService.convertToTcUserDto(tcUser);
		tcUserDto.setPhoto(tcUserDto.getPhoto());
		return new ResponseEntity<TcUserDto>(tcUserDto, HttpStatus.OK);
	}

	public ResponseEntity<?> getAll(String filter, Pageable paging, boolean withPicture) {
		Map<String, Object> response = new HashMap<>();
		Page<TcUser> pagedResult = null;
		List<TcUser> tcUsers;
		if (filter.isEmpty()) {
			if (paging == null) {
				tcUsers = tcUserRepository.findAll();
				if (withPicture) {
					for (TcUser tcUser : tcUsers) {
						tcUser.setPhoto(tcUser.getPhoto());
					}
				}
				response.put("data", mapperService.convertToTcUsersDto(tcUsers));
			} else {
				pagedResult = tcUserRepository.findAll(paging);
			}
		} else {
			filter = "%" + filter.replaceAll(" ", "%") + "%";
			if (paging == null) {
				tcUsers = tcUserRepository.findAllByFullnameLike(filter);
				if (withPicture) {
					for (TcUser tcUser : tcUsers) {
						tcUser.setPhoto(tcUser.getPhoto());
					}
				}
				response.put("data", mapperService.convertToTcUsersDto(tcUsers));
			} else {
				pagedResult = tcUserRepository.findAllByFullnameLike(filter, paging);
			}
		}
		if (paging != null) {
			tcUsers = pagedResult.getContent();
			if (withPicture) {
				for (TcUser tcUser : tcUsers) {
					tcUser.setPhoto(tcUser.getPhoto());
				}
			}
			response.put("data", mapperService.convertToTcUsersDto(tcUsers));
			response.put("currentPage", pagedResult.getNumber());
			response.put("totalItems", pagedResult.getTotalElements());
			response.put("totalPages", pagedResult.getTotalPages());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	public ResponseEntity<?> getAllActive() {
		List<TcUser> data = tcUserRepository.findAllByStatusId(RegisterStatus.active.getValue());
		return new ResponseEntity<List<?>>(mapperService.convertToTcUsersDto(data), HttpStatus.OK);
	}

	public ResponseEntity<?> loginUser(User user) {
		byte[] tmpBPass = Base64.getDecoder().decode(user.getPassword());
		String tmpPass = new String(tmpBPass);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), tmpPass));
		Optional<TcUser> tcUserOptional = tcUserRepository.findByUsername(user.getUsername());
		TcUser tcUser = tcUserOptional.get();
		if (tcUser.getStatusId() == RegisterStatus.unauthorized.getValue()) {
			return new ResponseEntity<String>(
					"Su usuario no está autorizado, debe esperar o comuníquese para consultar sobre el proceso",
					HttpStatus.UNAUTHORIZED);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateJwtToken(authentication, String.valueOf(tcUser.getUserId()));
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	public ApiResponse sendMailTest() {
		List<ObjParameter> parameters = new ArrayList<>();
		ObjParameter objParameter = new ObjParameter();
		objParameter.setField("username");
		objParameter.setValue("Melvin Cali");
		parameters.add(objParameter);
		objParameter = new ObjParameter();
		objParameter.setField("message");
		objParameter.setValue("Hola, has sido registrado al sistema. Tus credenciales son las siguientes:");
		parameters.add(objParameter);
		objParameter = new ObjParameter();
		objParameter.setField("url");
		objParameter.setValue(encryptService.urlSite);
		parameters.add(objParameter);
		try {
			mailService.sendEmail("mcalic1@miumg.edu.gt", "prueba", "register", parameters);
			return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage());
		} catch (Exception e) {
			return new ApiResponse(ResponseResult.fail.getValue(), e.getMessage());
		}
	}

	public ResponseEntity<?> deleteUser(Long userTokenId, Long userId) {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ResponseEntity<String>("Registro no encontrado", HttpStatus.BAD_REQUEST);
		}
		TcUser tcUser = tcUserOptional.get();
		tcUser.setStatusId(RegisterStatus.disabled.getValue());
		tcUser.setUpdatedBy(userTokenId);
		tcUserRepository.save(tcUser);
		return new ResponseEntity<String>(ResponseResult.success.getMessage(), HttpStatus.OK);
	}

}
