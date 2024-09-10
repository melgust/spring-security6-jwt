package gt.edu.umg.demodb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gt.edu.umg.demodb.model.TcDemo;

@Repository
public interface TcDemoRepository extends JpaRepository<TcDemo, Long> {
	
	List<TcDemo> findAllByStatusId(byte statusId);
    
}