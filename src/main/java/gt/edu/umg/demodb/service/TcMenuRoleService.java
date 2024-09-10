package gt.edu.umg.demodb.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gt.edu.umg.demodb.model.TcMenu;
import gt.edu.umg.demodb.model.TcMenuRole;
import gt.edu.umg.demodb.model.TcRole;
import gt.edu.umg.demodb.repository.TcMenuRoleRepository;
import gt.edu.umg.demodb.utils.ApiResponse;
import gt.edu.umg.demodb.utils.RegisterStatus;
import gt.edu.umg.demodb.utils.ResponseResult;

@Service
public class TcMenuRoleService {

	@Autowired
	TcMenuRoleRepository tcMenuRoleRepository;

	public ApiResponse setMenuRole(Long menuRoleId, TcMenuRole tcMenuRole) {
		tcMenuRole.setCreatedBy(menuRoleId);
		tcMenuRoleRepository.save(tcMenuRole);
		return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
	}

	public ApiResponse updMenuRole(Long menuRoleTokenId, Long menuRoleId, TcMenuRole tcMenuRole) {
		Optional<TcMenuRole> iu = tcMenuRoleRepository.findById(menuRoleId);
		if (iu.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		} else {
			TcMenuRole u = iu.get();
			if (u.getMenuRoleId() == tcMenuRole.getMenuRoleId()) {
				u.setStatusId(tcMenuRole.getStatusId());
				u.setTcRole(tcMenuRole.getTcRole());
				u.setUpdatedBy(menuRoleTokenId);
				u.setUpdatedAt(new Date());
				u.setUrlMenu(tcMenuRole.getUrlMenu());
				tcMenuRoleRepository.save(u);
				return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(),
						null);
			} else {
				return new ApiResponse(ResponseResult.fail.getValue(),
						"El identificador indicado no coincide con la informacion proporcionada", null);
			}
		}
	}

	public ApiResponse getMenuRoleById(Long menuRoleId) {
		Optional<TcMenuRole> tcMenuRole = tcMenuRoleRepository.findById(menuRoleId);
		if (tcMenuRole.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		} else {
			return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", tcMenuRole.get());
		}
	}

	public ApiResponse getAll(String filter, Pageable paging) {
		ApiResponse apiResponse;
		Map<String, Object> response = new HashMap<>();
		Page<TcMenuRole> pagedResult = null;
		if (filter.isEmpty()) {
			if (paging == null) {
				response.put("data", tcMenuRoleRepository.findAll());
			} else {
				pagedResult = tcMenuRoleRepository.findAll(paging);
			}
		}
		if (paging != null) {
			response.put("data", pagedResult.getContent());
			response.put("currentPage", pagedResult.getNumber());
			response.put("totalItems", pagedResult.getTotalElements());
			response.put("totalPages", pagedResult.getTotalPages());
		}
		apiResponse = new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", response);
		return apiResponse;
	}

	public ApiResponse getAllActive() {
		ApiResponse apiResponse;
		List<TcMenuRole> data = tcMenuRoleRepository.findAllByStatusId(RegisterStatus.active.getValue());
		apiResponse = new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", data);
		return apiResponse;
	}

	public ApiResponse getAllByTcRole(Long roleId, String filter, Pageable paging) {
		TcRole tcRole = new TcRole();
		tcRole.setRoleId(roleId);
		Map<String, Object> response = new HashMap<>();
		Page<TcMenuRole> pagedResult = null;
		if (filter.isEmpty()) {
			if (paging == null) {
				response.put("data", tcMenuRoleRepository.findAllByTcRole(tcRole));
			} else {
				pagedResult = tcMenuRoleRepository.findAllByTcRole(tcRole, paging);
			}
		}
		if (paging != null) {
			response.put("data", pagedResult.getContent());
			response.put("currentPage", pagedResult.getNumber());
			response.put("totalItems", pagedResult.getTotalElements());
			response.put("totalPages", pagedResult.getTotalPages());
		}
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", response);
	}

	public ApiResponse getAllByTcMenu(Long menuId, String filter, Pageable paging) {
		TcMenu tcMenu = new TcMenu();
		tcMenu.setMenuId(menuId);
		Map<String, Object> response = new HashMap<>();
		Page<TcMenuRole> pagedResult = null;
		if (filter.isEmpty()) {
			if (paging == null) {
				response.put("data", tcMenuRoleRepository.findAllByTcMenu(tcMenu));
			} else {
				pagedResult = tcMenuRoleRepository.findAllByTcMenu(tcMenu, paging);
			}
		}
		if (paging != null) {
			response.put("data", pagedResult.getContent());
			response.put("currentPage", pagedResult.getNumber());
			response.put("totalItems", pagedResult.getTotalElements());
			response.put("totalPages", pagedResult.getTotalPages());
		}
		return  new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", response);
	}

}
