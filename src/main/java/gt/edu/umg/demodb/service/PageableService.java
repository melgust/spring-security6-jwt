package gt.edu.umg.demodb.service;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PageableService {

	private String filter;

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public static <K, V> V getSingleValueFromKey(Map<K, V> map, K key) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (Objects.equals(key, entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public Pageable getPagination(Map<String, String> qparams, String defaultSortActive) {		
		Pageable paging = null;
		String filter = "";
		boolean paginate = qparams.size() > 0 ? true : false;
		if (paginate) {
			int pageIndex = (getSingleValueFromKey(qparams, "page").isEmpty()) ? 1
					: Integer.parseInt(getSingleValueFromKey(qparams, "page"));
			int pageSize = (getSingleValueFromKey(qparams, "pageSize").isEmpty()) ? 1
					: Integer.parseInt(getSingleValueFromKey(qparams, "pageSize"));
			String sortActive = (getSingleValueFromKey(qparams, "sortActive").isEmpty()) ? defaultSortActive
					: getSingleValueFromKey(qparams, "sortActive");
			String sortDirection = (getSingleValueFromKey(qparams, "sortDirection").isEmpty()) ? "desc"
					: getSingleValueFromKey(qparams, "sortDirection");
			filter = (getSingleValueFromKey(qparams, "filter").isEmpty()) ? ""
					: getSingleValueFromKey(qparams, "filter").trim().replace(" ", "%");
			Direction direction = (sortDirection.toLowerCase().equals("asc")) ? Sort.Direction.ASC
					: Sort.Direction.DESC;
			paging = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortActive));
		}
		setFilter(filter);
		return paging;
	}

}
