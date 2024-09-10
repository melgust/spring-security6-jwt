package gt.edu.umg.demodb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.umg.demodb.model.TcMenu;

public interface TcMenuRepository extends JpaRepository<TcMenu, Long> {

	List<TcMenu> findAllByMenuDescLike(String menuDesc);

	Page<TcMenu> findAllByMenuDescLike(String menuDesc, Pageable pageable);

	List<TcMenu> findAllByStatusId(byte statusId);

	Page<TcMenu> findAllByStatusId(byte statusId, Pageable pageable);

}
