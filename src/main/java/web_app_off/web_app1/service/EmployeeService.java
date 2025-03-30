package web_app_off.web_app1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web_app_off.web_app1.exception.UserNotfoundException;
import web_app_off.web_app1.model.Employee;
import web_app_off.web_app1.repo.EmployeeRepo;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public Employee addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        employee.setId(null); // Ensure ID is not set manually
        employee.setEmployeeCode(UUID.randomUUID().toString());

        try {
            return employeeRepo.save(employee);
        } catch (Exception e) {
            throw new RuntimeException("Error saving employee: " + e.getMessage());
        }
    }
    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }
    public Employee updateEmployee(Employee employee)
    {
        return employeeRepo.save(employee);
    }
    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteById(id);
    }
    public Employee findById(long id)
    {
        return employeeRepo.findById(id).orElseThrow(() -> new UserNotfoundException("User by id " + id + " was not found"));
    }
    public boolean existsById(Long id) {
        return employeeRepo.existsById(id);
    }
}
