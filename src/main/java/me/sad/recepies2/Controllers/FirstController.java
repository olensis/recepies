package me.sad.recepies2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping

    public String helloWord(){
        return "Привет, это приложение с рецептами";
    }

    @GetMapping("/info")

    public String Info(@RequestParam String name, @RequestParam String nameProject, @RequestParam Integer dateOfCreation, @RequestParam String  description ) {

        return "Привет, меня зовут: " + name + " название проекта " + nameProject + " дата создания " + dateOfCreation + " описание " + description;
    }

}

