package com.github.example.sampleurlhandlerfilter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slash")
public class SampleController {
    @GetMapping("/with/")
    public String with() {
        return "{}\n";
    }

    @GetMapping("/without")
    public String without() {
        return "{}\n";
    }
}
