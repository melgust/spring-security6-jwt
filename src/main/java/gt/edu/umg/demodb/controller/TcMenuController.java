package gt.edu.umg.demodb.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.jwt.JwtProvider;
import gt.edu.umg.demodb.model.TcMenu;
import gt.edu.umg.demodb.service.ErrorManagerService;
import gt.edu.umg.demodb.service.PageableService;
import gt.edu.umg.demodb.service.TcMenuService;
import gt.edu.umg.demodb.utils.ApiResponse;

@RestController
@RequestMapping("/menu")
public class TcMenuController {
	@Autowired
	ErrorManagerService errorManagerService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PageableService pageableService;

	@Autowired
	TcMenuService tcMenuService;

	@PostMapping("/add")
	public ResponseEntity<?> setMenu(Authentication authentication, HttpServletRequest request,
			@Valid @RequestBody TcMenu tcMenu) {
		ApiResponse apiResponse;
		try {
			long userId = jwtProvider.getUserIdFromJwt(authentication, request);
			apiResponse = tcMenuService.setMenu(userId, tcMenu);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{menuId}")
	public ResponseEntity<?> updMenu(Authentication authentication, HttpServletRequest request,
			@PathVariable(value = "menuId") Long menuId, @Valid @RequestBody TcMenu tcMenu) {
		ApiResponse apiResponse;
		try {
			long menuTokenId = jwtProvider.getUserIdFromJwt(authentication, request);
			apiResponse = tcMenuService.updMenu(menuTokenId, menuId, tcMenu);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{menuId}")
	public ResponseEntity<?> getMenuById(@PathVariable(value = "menuId") Long menuId) {
		ApiResponse apiResponse;
		try {
			apiResponse = tcMenuService.getMenuById(menuId);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(@RequestParam(required = false) Map<String, String> qparams) {
		ApiResponse apiResponse;
		try {
			Pageable paging = pageableService.getPagination(qparams, "menuId");
			String filter = pageableService.getFilter();
			apiResponse = tcMenuService.getAll(filter, paging);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all/active")
	public ResponseEntity<?> getAllActive() {
		ApiResponse apiResponse;
		try {
			apiResponse = tcMenuService.getAllActive();
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
