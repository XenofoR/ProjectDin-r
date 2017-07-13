package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.Set;

@Controller
public class GreetingController2 {

    @Autowired
    private ApplicationContext servletContext;


    @RequestMapping("/result")
    public @ResponseBody Set<String> result(@RequestParam(value="date", required=false, defaultValue="World") String date, Model model) {
        model.addAttribute("date", date);


        RedisOperations template = servletContext.getBean(RedisOperations.class);

        Set<String> lunches = template.keys(date+"*");




        return lunches;
    }

}
