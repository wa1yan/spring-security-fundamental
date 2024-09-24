package dev.waiyanhtet.springsecuritylesson7.controller;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GreetController {

    @GetMapping("/greet1")
    @PreAuthorize("hasAuthority('read')")
    public String greet1(String name) {
        return "Greet 1";
    }

    @GetMapping("/greet2")
    @PreAuthorize("hasAuthority('write')")
    public String greet2(String name) {
        return "Greet 2";
    }

    @GetMapping("/greet3")
    @PreAuthorize("hasAnyAuthority('read', 'write')")
    public String greet3(String name) {
        return "Greet 3";
    }

    @GetMapping("/greet4")
    @PreAuthorize("#name == authentication.name")
    public String greet4(@RequestParam String name) {
        return "Greet 4";
    }

    @GetMapping("/greet5")
    @PreFilter("filterObject.contains('a')") //filter to only contains a list
    public String greet5(@RequestBody List<String> nameList) {
        System.out.println(nameList.toString());
        return "Greet 5";
    }

    @GetMapping("/greet7")
    @PostFilter("filterObject.contains('a')")
    public List<String> greet7() {
        var list = new ArrayList<String>();
        list.add("ab");
        list.add("ca");
        list.add("ed");
        list.add("fe");
        return list;
    }

    @GetMapping("/greet8")
    @PreAuthorize("@customSecurityFilter.condition()")
    public String greet8() {
        return "Greet 8";
    }
}
