package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;

@Controller
public class GreetingController2 {

    @Autowired
    private ApplicationContext servletContext;


    @RequestMapping("/greeting2")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", "New value");



        StringRedisTemplate template = servletContext.getBean(StringRedisTemplate.class);

        template.boundValueOps("myKey2").set(name);
        return "greeting";
    }

}
