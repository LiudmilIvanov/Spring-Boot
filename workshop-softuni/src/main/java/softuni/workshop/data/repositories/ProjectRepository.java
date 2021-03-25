package softuni.workshop.data.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softuni.workshop.data.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findByName(String name);
	
	Set<Project> findAllByFinishedTrue();
}
