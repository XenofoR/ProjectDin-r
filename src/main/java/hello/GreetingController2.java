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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class GreetingController2 {

    @Autowired
    private ApplicationContext servletContext;


    @RequestMapping(value = "/result", produces = "application/json")
    public @ResponseBody List<Suggestion> result(@RequestParam(value="date", required=false, defaultValue="World") String date, Model model) {
        model.addAttribute("date", date);


        RedisOperations template = servletContext.getBean(RedisOperations.class);

        Set<String> lunches = template.keys(date+"*");
        List<Suggestion> results = new ArrayList<>();

        if (lunches!= null && lunches.size()>0) {


            results= template.opsForValue().multiGet(lunches);//.forEach(e -> results.add(e.toString()));

//        results.add(results2.get(0).toString());

            System.out.println(results.get(0));
        }


        return results;
    }

}
