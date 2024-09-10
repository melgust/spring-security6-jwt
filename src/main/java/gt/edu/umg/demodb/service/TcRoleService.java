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

import gt.edu.umg.demodb.model.TcRole;
import gt.edu.umg.demodb.repository.TcRoleRepository;
import gt.edu.umg.demodb.utils.ApiResponse;
import gt.edu.umg.demodb.utils.RegisterStatus;
import gt.edu.umg.demodb.utils.ResponseResult;

@Service
public class TcRoleService {

	@Autowired
	TcRoleRepository tcRoleRepository;

	@Autowired
	ErrorManagerService errorManagerService;

	public ApiResponse setRole(Long userId, TcRole tcRole) {
		ApiResponse apiResponse;
		tcRole.setCreatedBy(userId);
		tcRoleRepository.save(tcRole);
		apiResponse = new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
		return apiResponse;
	}

	public ApiResponse updRole(Long roleTokenId, Long roleId, TcRole tcRoleIn) {
		Optional<TcRole> tcRoleOptional = tcRoleRepository.findById(roleId);
		if (tcRoleOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		TcRole tcRole = tcRoleOptional.get();
		tcRole.setStatusId(tcRoleIn.getStatusId());
		tcRole.setRoleDesc(tcRoleIn.getRoleDesc());
		tcRole.setUpdatedBy(roleTokenId);
		tcRole.setUpdatedAt(new Date());
		tcRoleRepository.save(tcRole);
		return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
	}

	public ApiResponse getRoleById(Long roleId) {
		Optional<TcRole> tcRoleOptional = tcRoleRepository.findById(roleId);
		if (tcRoleOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", tcRoleOptional.get());
	}

	public ApiResponse getAll(String filter, Pageable paging) {
		ApiResponse apiResponse;
		Map<String, Object> response = new HashMap<>();
		Page<TcRole> pagedResult = null;
		if (filter.isEmpty()) {
			if (paging == null) {
				response.put("data", tcRoleRepository.findAll());
			} else {
				pagedResult = tcRoleRepository.findAll(paging);
			}
		} else {
			filter = "%" + filter.replaceAll(" ", "%") + "%";
			if (paging == null) {
				response.put("data", tcRoleRepository.findAllByRoleDescLike(filter));
			} else {
				pagedResult = tcRoleRepository.findAllByRoleDescLike(filter, paging);
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
		List<TcRole> data = tcRoleRepository.findAllByStatusId(RegisterStatus.active.getValue());
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", data);
	}

}
