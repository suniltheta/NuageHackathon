package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerAController {

    @RequestMapping("/D")
    public Double a(@RequestParam(value="x", defaultValue="0") String x) 
    {
    	Integer X = Integer.parseInt(x);
	retrurn Math.log(X);
    }
    
}
