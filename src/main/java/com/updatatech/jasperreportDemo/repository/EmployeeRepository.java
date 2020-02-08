package com.updatatech.jasperreportDemo.repository;

import com.updatatech.jasperreportDemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(long id);

//    List<Employee> findByCategoryId(Long id);
}
