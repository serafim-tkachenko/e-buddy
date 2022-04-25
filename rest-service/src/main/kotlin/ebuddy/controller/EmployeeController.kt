package ebuddy.controller

import ebuddy.model.Employee
import ebuddy.repository.EmployeeRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController(
    val employeeRepository: EmployeeRepository
) {

    val logger: Logger = LoggerFactory.getLogger(EmployeeController::class.java)

    @PutMapping(value = ["/getOrCreateEmployee"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrCreateEmployeeEntry(@RequestBody data: UserGeneralData): Employee {
        logger.info("Checking or registering of employee entry for id: ${data.id}, name: ${data.name}")
        return employeeRepository.findByIdOrNull(data.id)?.also {
            logger.info("Employee was successfully found, id: ${it.id}, name: ${it.name}")
        } ?: employeeRepository.insert(
            Employee(
                id = data.id,
                name = data.name,
                vacations = emptyList()
            )
        ).also {
            logger.info("Employee was successfully created in the database, id: ${it.id}, name: ${it.name}")
        }
    }

}

data class UserGeneralData(
    val id: String,
    val name: String
)