package com.sporty;

import io.micronaut.http.annotation.*;

@Controller("/sporty-f1")
public class SportyF1Controller {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}