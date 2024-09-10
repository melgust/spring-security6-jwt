package gt.edu.umg.demodb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.umg.demodb.model.TcRole;

public interface TcRoleRepository extends JpaRepository<TcRole, Long> {
	
	List<TcRole> findAllByRoleDescLike(String roleDesc);

	Page<TcRole> findAllByRoleDescLike(String roleDesc, Pageable pageable);
	
	List<TcRole> findAllByStatusId(byte statusId);

	Page<TcRole> findAllByStatusId(byte statusId, Pageable pageable);
	
	
}
