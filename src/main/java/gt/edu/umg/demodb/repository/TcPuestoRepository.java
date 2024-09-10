package gt.edu.umg.demodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.demodb.model.TcPuesto;

@Repository
public interface TcPuestoRepository extends JpaRepository<TcPuesto, Long> {
	
}