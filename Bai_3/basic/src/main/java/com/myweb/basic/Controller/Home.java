package com.myweb.basic.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
public class Home {

    @GetMapping("/demo/hello")
    @ResponseBody
    public ResponseEntity<String> displayHelloMe(@RequestParam String name, Integer age) {
        String aString = "Hello " + name + ", " + age + " year old";
        return new ResponseEntity<>(aString, HttpStatus.OK);
    }

    @GetMapping("demo/product")
    public ResponseEntity<List<String>> listProducts() {
        List<String> listProducts = new ArrayList<>();
        listProducts.add("Honda");
        listProducts.add("Toyota");
        listProducts.add("Suzuki");
        return new ResponseEntity<>(listProducts, HttpStatus.OK);

    }

    @GetMapping("/demo/page_1")
    public String page_1() {
        return "page_1";
    }

    @GetMapping("/demo/unit1_1")
    public String unit1_1() {
        return "unit1_1";
    }

    @GetMapping("/demo/unit1_2")
    public String unit1_2() {
        return "unit1_2";
    }

    @GetMapping("/demo/unit1_3")
    public String unit1_3() {
        return "unit1_3";
    }

    @GetMapping("/demo/unit1_4")
    public String unit1_4() {
        return "unit1_4";
    }

    @GetMapping("demo/unit1_5")
    public String unit1_5(Model model) {
        int a = 3;
        int b = 4;
        model.addAttribute("sothunhat", a);
        model.addAttribute("sothuhai", b);

        return "unit1_5";
    }
}

