package ru.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.service.RabbitService;

/**
 * Created by rakhmetov on 02.02.17.
 */
@RestController
public class Controller {

    @Autowired
    private RabbitService rabbitService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("message") String message) throws JsonProcessingException {
        rabbitService.publishMessage(message);
        return "ok";
    }
}
