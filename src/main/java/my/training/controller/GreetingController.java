package my.training.controller;

import my.training.builder.GreetingBuilder;
import my.training.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

@RestController
//Maps all types of Requests: GET, PUT, POST
@RequestMapping(value = "/greeting",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)//only return json and we can set to xml
public class GreetingController {
    public static final String TEMPLATE = "Hello, %s!";
    private final GreetingBuilder greetingBuilder;
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public GreetingController(GreetingBuilder greetingBuilder) {
        this.greetingBuilder = greetingBuilder;
    }

    @GetMapping
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return greetingBuilder
                .setId(counter.incrementAndGet())
                .withContent(format(TEMPLATE, name))
                .build();
    }
}