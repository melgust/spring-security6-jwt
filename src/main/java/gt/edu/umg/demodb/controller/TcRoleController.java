package gt.edu.umg.demodb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.demodb.jwt.JwtProvider;
import gt.edu.umg.demodb.service.ErrorManagerService;
import gt.edu.umg.demodb.service.PageableService;
import gt.edu.umg.demodb.service.TcRoleService;
import gt.edu.umg.demodb.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/role")
public class TcRoleController {

	@Autowired
	ErrorManagerService errorManagerService;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	PageableService pageableService;

	@Autowired
	TcRoleService tcRoleService;

	@GetMapping("/all")
	public ResponseEntity<?> getAll(Authentication authentication, HttpServletRequest request,
			@RequestParam(required = false) Map<String, String> qparams) {
		ApiResponse apiResponse;
		try {
			Pageable paging = pageableService.getPagination(qparams, "roleId");
			String filter = pageableService.getFilter();
			apiResponse = tcRoleService.getAll(filter, paging);
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(errorManagerService.managerException(e),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
