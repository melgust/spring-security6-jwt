package gt.edu.umg.demodb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gt.edu.umg.demodb.model.TcMenu;
import gt.edu.umg.demodb.model.TcMenuRole;
import gt.edu.umg.demodb.model.TcRole;

public interface TcMenuRoleRepository extends JpaRepository<TcMenuRole, Long> {

	List<TcMenuRole> findAllByTcRole(TcRole tcRole);

	List<TcMenuRole> findAllByTcRoleAndStatusId(TcRole tcRole, byte statusId);

	List<TcMenuRole> findAllByTcRoleInAndStatusId(List<TcRole> roles, byte statusId);

	Page<TcMenuRole> findAllByTcRole(TcRole tcRole, Pageable pageable);

	List<TcMenuRole> findAllByStatusId(byte statusId);

	Page<TcMenuRole> findAllByStatusId(byte statusId, Pageable pageable);

	Page<TcMenuRole> findAllByTcMenu(TcMenu tcMenu, Pageable pageable);

	List<TcMenuRole> findAllByTcMenu(TcMenu tcMenu);

	@Query("SELECT rm FROM TcMenuRole rm INNER JOIN rm.tcMenu tm WHERE rm.tcRole = :tcRole AND rm.statusId = 1 AND tm.tcFather IS NULL AND tm.statusId = 1")
	List<TcMenuRole> findAllByRootMenu(@Param("tcRole") TcRole tcRole);

	@Query("SELECT rm FROM TcMenuRole rm INNER JOIN rm.tcMenu tm WHERE rm.tcRole = :tcRole AND rm.statusId = 1 AND tm.tcFather = :tcMenu And tm.statusId = 1")
	List<TcMenuRole> findByTcFatherAndTcRole(@Param("tcMenu") TcMenu tcMenu, @Param("tcRole") TcRole tcRole);

}
