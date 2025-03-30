package web_app_off.web_app1;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web_app_off.web_app1.model.Employee;
import web_app_off.web_app1.repo.EmployeeRepo;
import web_app_off.web_app1.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employee")
public class Employeeresource {
    private final EmployeeService employeeRepo;

    Employeeresource(EmployeeService employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employees = employeeRepo.findAllEmployees();
        return (new ResponseEntity<>(employees, HttpStatus.OK));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id)
    {
        Employee employee = employeeRepo.findById(id);
        return (new ResponseEntity<>(employee, HttpStatus.OK));
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee)
    {
        return new ResponseEntity<Employee> (employeeRepo.addEmployee(employee), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        // Check if the employee exists
        if (!employeeRepo.existsById(employee.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update employee
        Employee updatedEmployee = employeeRepo.updateEmployee(employee);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        System.out.println("🛠 Attempting to delete employee with ID: " + id);

        if (!employeeRepo.existsById(id)) {
            System.out.println("❌ Employee not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }

        employeeRepo.deleteEmployee(id);
        System.out.println("✅ Employee deleted successfully.");

        // ✅ Fix: Return a proper response body
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
