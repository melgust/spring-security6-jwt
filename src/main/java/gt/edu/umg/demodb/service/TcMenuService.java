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
import gt.edu.umg.demodb.repository.TcMenuRepository;
import gt.edu.umg.demodb.utils.ApiResponse;
import gt.edu.umg.demodb.utils.RegisterStatus;
import gt.edu.umg.demodb.utils.ResponseResult;

@Service
public class TcMenuService {

	@Autowired
	TcMenuRepository tcMenuRepository;

	public ApiResponse setMenu(Long menuId, TcMenu tcMenu) {
		ApiResponse apiResponse;
		tcMenu.setCreatedBy(menuId);
		tcMenuRepository.save(tcMenu);
		apiResponse = new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);
		return apiResponse;
	}

	public ApiResponse updMenu(Long menuTokenId, Long menuId, TcMenu tcMenuIn) {
		Optional<TcMenu> tcMenuOptional = tcMenuRepository.findById(menuId);
		if (tcMenuOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		TcMenu tcMenu = tcMenuOptional.get();
		if (tcMenu.getMenuId() == tcMenuIn.getMenuId()) {
			return new ApiResponse(ResponseResult.fail.getValue(),
					"El identificador indicado no coincide con la informacion proporcionada", null);
		}
		tcMenu.setStatusId(tcMenuIn.getStatusId());
		tcMenu.setIcon(tcMenuIn.getIcon());
		tcMenu.setIconColor(tcMenuIn.getIconColor());
		tcMenu.setMenuDesc(tcMenuIn.getMenuDesc());
		tcMenu.setShortName(tcMenuIn.getShortName());
		tcMenu.setTcFather(tcMenuIn.getTcFather());
		tcMenu.setTextColor(tcMenuIn.getTextColor());
		tcMenu.setTooltip(tcMenuIn.getTooltip());
		tcMenu.setUpdatedBy(menuTokenId);
		tcMenu.setUpdatedAt(new Date());
		tcMenuRepository.save(tcMenu);
		return new ApiResponse(ResponseResult.success.getValue(), ResponseResult.success.getMessage(), null);

	}

	public ApiResponse getMenuById(Long menuId) {
		Optional<TcMenu> tcMenuOptional = tcMenuRepository.findById(menuId);
		if (tcMenuOptional.isEmpty()) {
			return new ApiResponse(ResponseResult.fail.getValue(), "Registro no encontrado", null);
		}
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", tcMenuOptional.get());
	}

	public ApiResponse getAll(String filter, Pageable paging) {
		Map<String, Object> response = new HashMap<>();
		Page<TcMenu> pagedResult = null;
		if (filter.isEmpty()) {
			if (paging == null) {
				response.put("data", tcMenuRepository.findAll());
			} else {
				pagedResult = tcMenuRepository.findAll(paging);
			}
		} else {
			filter = "%" + filter.replaceAll(" ", "%") + "%";
			if (paging == null) {
				response.put("data", tcMenuRepository.findAllByMenuDescLike(filter));
			} else {
				pagedResult = tcMenuRepository.findAllByMenuDescLike(filter, paging);
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

	public ApiResponse getAllActive() {
		List<TcMenu> data = tcMenuRepository.findAllByStatusId(RegisterStatus.active.getValue());
		return new ApiResponse(ResponseResult.success.getValue(), "Datos cargados", data);
	}

}
