package ebuddy.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping(value =["/hi"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun sayHi(): String {
        return "Hi!"
    }

}