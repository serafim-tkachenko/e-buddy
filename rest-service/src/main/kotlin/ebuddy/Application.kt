package ebuddy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories(basePackages = ["ebuddy.repository"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}