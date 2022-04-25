package ebuddy.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document
data class Employee(
    @Id
    val id: String,
    val name: String,
    val vacations: List<Vacation>
)

data class Vacation(
    val id: Long,
    val isConfirmed: Boolean,
    val comment: String,
    val dates: List<Date>
)