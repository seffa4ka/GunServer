package th.seffka.gun.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gun")
public class GunController {
    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String getTest(ModelMap mdel) {
        return "ok";
    }
}
