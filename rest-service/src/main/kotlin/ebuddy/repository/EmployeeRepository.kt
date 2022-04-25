package ebuddy.repository

import ebuddy.model.Employee
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository: MongoRepository<Employee, String>