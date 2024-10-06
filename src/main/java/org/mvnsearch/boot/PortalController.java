package org.mvnsearch.boot;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.Callable;


@RestController
public class PortalController {

    @GetMapping("/")
    public String index() throws InterruptedException {
        sayHi("Jackie");
        int sleepms=new Random().nextInt(10000);
        Thread.sleep(sleepms);
        return "Hello Virtual Thread! " + sleepms+ "ms";
    }

    @GetMapping("/where-am-i")
    public String getThreadName() {
        return Thread.currentThread().toString();
    }

    @GetMapping("/where-am-i-async")
    public Callable<String> getAsyncThreadName() {
        return () -> Thread.currentThread().toString();
    }

    @GetMapping("/reactive")
    public Mono<String> reactive() {
        return Mono.just("Hello Reactor!").doOnNext(s -> {
            System.out.println("Reactive on " + Thread.currentThread());
        });
    }

    @Async
    public void sayHi(String name) {
        System.out.println("Hi " + name + ", on " + Thread.currentThread());
    }
}
