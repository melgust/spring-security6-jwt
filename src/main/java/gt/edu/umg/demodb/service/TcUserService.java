package gt.edu.umg.demodb.service;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(rollbackFor = { Exception.class })
	public ApiResponse setUser(TcUser tcUser, long userTokenId) throws Exception {
		tcUser.setEmail(tcUser.getEmail().trim().toLowerCase());
		tcUser.setUsername(tcUser.getUsername().trim().toLowerCase());
		Optional<TcUser> tcUserOptional = tcUserRepository.findByUsername(tcUser.getUsername());
		if (!tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "El usuario ya existe, favor de verificar", null);
		}
		tcUserOptional = tcUserRepository.findByEmail(tcUser.getEmail());
		if (!tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "El correo electrónico ya se encuentra registrado",
					null);
		}
		tcUserOptional = tcUserRepository.findByDocumentNumber(tcUser.getDocumentNumber());
		if (!tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "El número de documento ya se encuentra registrado",
					null);
		}
		String password = passwordGeneratorService.generatePassword(8, KeyDictionary.STRONG);
		tcUser.setPassword(passwordEncoder.encode(password));
		tcUser.setStatusId(RegisterStatus.active.getValue());
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
			return new ApiResponse(ResponseResult.success.getValue(),
					"Usuario creado exitosamente, confirme su solicitud a través del correo enviado", password);
		} catch (Exception e) {
			return new ApiResponse(ResponseResult.success.getValue(),
					"No fue posible enviar el correo de notificación, comuníquese con el administrador del sistema",
					password);
		}
	}

	public ApiResponse updateUser(Long userTokenId, Long userId, TcUser tcUserIn) {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		TcUser tcUser = tcUserOptional.get();
		tcUser.setUpdatedBy(userTokenId);
		tcUser.setUpdatedAt(new Date());
		if (tcUserIn.getPhoto() != null) {
			tcUser.setPhoto(tcUserIn.getPhoto());
		}
		tcUser.setEmail(tcUserIn.getEmail());
		tcUser.setBirthday(tcUserIn.getBirthday());
		tcUser.setDocumentNumber(tcUser.getDocumentNumber());
		tcUser.setFullname(tcUserIn.getFullname());
		tcUser.setPhone(tcUserIn.getPhone());
		if (tcUserIn.getTcRole() != null) {
			tcUser.setTcRole(tcUserIn.getTcRole());
		}
		tcUserRepository.save(tcUser);
		return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
	}

	public ApiResponse getUserById(Long userId) {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		TcUser tcUser = tcUserOptional.get();
		tcUser.setPhoto(tcUser.getPhoto());
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", tcUser);
	}

	public ApiResponse getAll(String filter, Pageable paging, boolean withPicture) {
		ApiResponse apiResponse;
		Map<String, Object> response = new HashMap<>();
		Page<TcUser> pagedResult = null;
		List<TcUser> tcUser;
		if (filter.isEmpty()) {
			if (paging == null) {
				tcUser = tcUserRepository.findAll();
				if (withPicture) {
					for (TcUser u : tcUser) {
						u.setPhoto(u.getPhoto());
					}
				}
				response.put("data", tcUser);
			} else {
				pagedResult = tcUserRepository.findAll(paging);
			}
		} else {
			filter = "%" + filter.replaceAll(" ", "%") + "%";
			if (paging == null) {
				tcUser = tcUserRepository.findAllByFullnameLike(filter);
				if (withPicture) {
					for (TcUser u : tcUser) {
						u.setPhoto(u.getPhoto());
					}
				}
				response.put("data", tcUser);
			} else {
				pagedResult = tcUserRepository.findAllByFullnameLike(filter, paging);
			}
		}
		if (paging != null) {
			tcUser = pagedResult.getContent();
			if (withPicture) {
				for (TcUser u : tcUser) {
					u.setPhoto(u.getPhoto());
				}
			}
			response.put("data", tcUser);
			response.put("currentPage", pagedResult.getNumber());
			response.put("totalItems", pagedResult.getTotalElements());
			response.put("totalPages", pagedResult.getTotalPages());
		}
		apiResponse = new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", response);
		return apiResponse;
	}

	public ApiResponse getAllActive() {
		List<TcUser> data = tcUserRepository.findAllByStatusId(RegisterStatus.active.getValue());
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", data);
	}

	public ApiResponse loginUser(User user) {
		byte[] tmpBPass = Base64.getDecoder().decode(user.getPassword());
		String tmpPass = new String(tmpBPass);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), tmpPass));
		Optional<TcUser> tcUserOptional = tcUserRepository.findByUsername(user.getUsername());
		TcUser tcUser = tcUserOptional.get();
		if (tcUser.getStatusId() == RegisterStatus.unauthorized.getValue()) {
			return new ApiResponse(ResponseResult.fail.getValue(),
					"Su usuario no está autorizado, debe esperar o comuníquese para consultar sobre el proceso", null);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateJwtToken(authentication, String.valueOf(tcUser.getUserId()));
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", token);
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

	public ApiResponse deleteUser(Long userTokenId, Long userId) {
		Optional<TcUser> tcUserOptional = tcUserRepository.findById(userId);
		if (tcUserOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		TcUser tcUser = tcUserOptional.get();
		tcUser.setStatusId(RegisterStatus.disabled.getValue());
		tcUser.setUpdatedBy(userTokenId);
		tcUserRepository.save(tcUser);
		return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
	}

}
