package gt.edu.umg.demodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.demodb.model.TcEmpleado;

@Repository
public interface TcEmpleadoRepository extends JpaRepository<TcEmpleado, Long> {
	
}