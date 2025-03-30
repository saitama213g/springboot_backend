package web_app_off.web_app1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web_app_off.web_app1.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteById(Long id);
    Optional<Employee> findById(long id);
}
