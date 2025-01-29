package gt.edu.umg.demodb.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.dto.TcUserDto;
import gt.edu.umg.demodb.jwt.JwtProvider;
import gt.edu.umg.demodb.jwt.User;
import gt.edu.umg.demodb.service.ErrorManagerService;
import gt.edu.umg.demodb.service.PageableService;
import gt.edu.umg.demodb.service.TcUserService;
import gt.edu.umg.demodb.utils.ApiResponse;

@RestController
@RequestMapping("/user")
public class TcUserController {

	@Autowired
	ErrorManagerService errorManagerService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PageableService pageableService;

	@Autowired
	TcUserService tcUserService;

	//@PreAuthorize("hasRole('UMG001')")
	@PostMapping("/add")
	public ResponseEntity<?> setUser(Authentication authentication, HttpServletRequest request,
			@Valid @RequestBody TcUserDto tcUserDto) {
		try {
			//long userId = jwtProvider.getUserIdFromJwt(authentication, request);
			return tcUserService.setUser(tcUserDto, 0);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('UMG001')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody TcUserDto tcUserDto) {
		try {
			return tcUserService.setUser(tcUserDto, 0);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('UMG001')")
	@PutMapping("/{userId}")
	public ResponseEntity<?> updUser(Authentication authentication, HttpServletRequest request,
			@PathVariable(value = "userId") Long userId, @Valid @RequestBody TcUserDto tcUserDto) {
		try {
			long userTokenId = jwtProvider.getUserIdFromJwt(authentication, request);
			return tcUserService.updateUser(userTokenId, userId, tcUserDto);			
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId) {		
		try {
			return tcUserService.getUserById(userId);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(Authentication authentication, HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> qparams) {		
		try {
			Pageable paging = pageableService.getPagination(qparams, "userId");
			String filter = pageableService.getFilter();
			return tcUserService.getAll(filter, paging, false);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('UMG001')")
	@GetMapping("/all/active")
	public ResponseEntity<?> getAllActive() {		
		try {
			return tcUserService.getAllActive();
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('UMG001')")
	@GetMapping("/all/picture")
	public ResponseEntity<?> getAllWithPicture(Authentication authentication, HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> qparams) {
		try {
			Pageable paging = pageableService.getPagination(qparams, "userId");
			String filter = pageableService.getFilter();
			return tcUserService.getAll(filter, paging, true);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('UMG001')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(Authentication authentication, HttpServletRequest request,
			@PathVariable(value = "userId") Long userId) {
		try {
			long userTokenId = jwtProvider.getUserIdFromJwt(authentication, request);
			return tcUserService.deleteUser(userTokenId, userId);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> setUser(@Valid @RequestBody User user) {
		try {
			return tcUserService.loginUser(user);
		} catch (Exception e) {
			if (e.getMessage().contains("Bad credentials")) {
				return new ResponseEntity<String>("El usuario o la clave no son correctas",
						HttpStatus.UNAUTHORIZED);
			} else {
				return new ResponseEntity<String>(errorManagerService.managerException(e),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/mail/test")
	public ResponseEntity<?> sendMailTest() {
		ApiResponse apiResponse;
		try {
			apiResponse = tcUserService.sendMailTest();
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
