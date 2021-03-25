package softuni.workshop.data.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import softuni.workshop.data.entities.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

	Set<Employee> findAllByAgeGreaterThan(int age);

}
