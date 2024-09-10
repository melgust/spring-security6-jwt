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

import gt.edu.umg.demodb.jwt.JwtProvider;
import gt.edu.umg.demodb.jwt.User;
import gt.edu.umg.demodb.model.TcUser;
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
			@Valid @RequestBody TcUser tcUser) {
		ApiResponse apiResponse;
		try {
			//long userId = jwtProvider.getUserIdFromJwt(authentication, request);
			apiResponse = tcUserService.setUser(tcUser, 0);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('A001')")
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody TcUser tcUser) {
		ApiResponse apiResponse;
		try {
			apiResponse = tcUserService.setUser(tcUser, 0);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('A001')")
	@PutMapping("/{userId}")
	public ResponseEntity<?> updUser(Authentication authentication, HttpServletRequest request,
			@PathVariable(value = "userId") Long userId, @Valid @RequestBody TcUser tcUser) {
		ApiResponse apiResponse;
		try {
			long userTokenId = jwtProvider.getUserIdFromJwt(authentication, request);
			apiResponse = tcUserService.updateUser(userTokenId, userId, tcUser);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "userId") Long userId) {
		ApiResponse apiResponse;
		try {
			apiResponse = tcUserService.getUserById(userId);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(Authentication authentication, HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> qparams) {
		ApiResponse apiResponse;
		try {
			Pageable paging = pageableService.getPagination(qparams, "userId");
			String filter = pageableService.getFilter();
			apiResponse = tcUserService.getAll(filter, paging, false);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('A001')")
	@GetMapping("/all/active")
	public ResponseEntity<?> getAllActive() {
		ApiResponse apiResponse;
		try {
			apiResponse = tcUserService.getAllActive();
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('A001')")
	@GetMapping("/all/picture")
	public ResponseEntity<?> getAllWithPicture(Authentication authentication, HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> qparams) {
		ApiResponse apiResponse;
		try {
			Pageable paging = pageableService.getPagination(qparams, "userId");
			String filter = pageableService.getFilter();
			apiResponse = tcUserService.getAll(filter, paging, true);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('A001')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(Authentication authentication, HttpServletRequest request,
			@PathVariable(value = "userId") Long userId) {
		ApiResponse apiResponse;
		try {
			long userTokenId = jwtProvider.getUserIdFromJwt(authentication, request);
			apiResponse = tcUserService.deleteUser(userTokenId, userId);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> setUser(@Valid @RequestBody User user) {
		ApiResponse apiResponse;
		try {
			apiResponse = tcUserService.loginUser(user);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
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
