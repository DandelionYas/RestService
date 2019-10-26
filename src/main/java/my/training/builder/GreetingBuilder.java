package my.training.builder;

import my.training.model.Greeting;
import org.springframework.stereotype.Component;

@Component
public class GreetingBuilder {
    private long id;
    private String content;

    public GreetingBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GreetingBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public Greeting build() {
        Greeting greeting = new Greeting();
        greeting.setId(id);
        greeting.setContent(content);
        return greeting;
    }
}