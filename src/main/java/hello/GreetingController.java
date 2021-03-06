package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Scope("prototype")
public class GreetingController {

    @Autowired
    private ApplicationContext servletContext;

    ///
    ///Expect to have a date in format 20170705 as a input


    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="date", required=false, defaultValue="World") String date, Model model) {
        model.addAttribute("date", date);


        RedisOperations template = servletContext.getBean(RedisOperations.class);

        Set<String> lunches = template.keys(date+"*");

        model.addAttribute("lunches", lunches);

        int counter = lunches.stream  ().map(e->e.split(":")).map(e->Integer.parseInt(e[1])).mapToInt(Integer::intValue).max().orElse(0);;// forEach(e->System.out.println(e));
        counter++;
        System.out.println(counter);

        Suggestion s = new Suggestion(counter);

        s.setFood("burger");
        s.setResturant("gnarlyBurger");
        s.addParticipant("Kim Hansson");


        /*template.opsForValue().set(date+":"+counter,s);
        Suggestion sf = (Suggestion)template.opsForValue().get("20170714:7");

        sf.setFood("program chaned");

        template.opsForValue().set("20170714:7",sf);*/

        return "greeting";
    }

}
