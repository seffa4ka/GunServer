package th.seffka.gun.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gun")
public class GunController {
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String getTest() {
        return "ok";
    }
}
