package gt.edu.umg.demodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.umg.demodb.model.TcRole;
import gt.edu.umg.demodb.model.TcUser;

public interface TcUserRepository extends JpaRepository<TcUser, Long> {

	Optional<TcUser> findByUsername(String username);

	Optional<TcUser> findByEmail(String email);

	Optional<TcUser> findByDocumentNumber(long documentNumber);

	List<TcUser> findAllByStatusId(byte statusId);

	Page<TcUser> findAllByStatusId(byte statusId, Pageable pageable);

	List<TcUser> findAllByFullnameLike(String fullname);

	Page<TcUser> findAllByFullnameLike(String fullname, Pageable pageable);

	List<TcUser> findAllByEmailOrDocumentNumber(String email, long documentNumber);

	Optional<TcUser> findByRetypePassword(String retypePassword);

	List<TcUser> findAllByTcRoleAndStatusId(TcRole tcRole, byte statusId);

	List<TcUser> findAllByTcRoleInAndStatusId(List<TcRole> roles, byte statusId);

	List<TcUser> findAllByTcRoleInAndStatusIdAndFullnameLikeIgnoreCase(List<TcRole> roles, byte statusId,
			String fullname);

	Page<TcUser> findAllByTcRoleInAndStatusId(List<TcRole> roles, byte statusId, Pageable pageable);

	Page<TcUser> findAllByTcRoleInAndStatusIdAndFullnameLikeIgnoreCase(List<TcRole> roles, byte statusId,
			String fullname, Pageable pageable);

}
