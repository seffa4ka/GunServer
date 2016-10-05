package th.seffka.gun.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.seffka.gun.server.entity.Test;
import th.seffka.gun.server.service.TestService;

import java.util.List;

@RestController
public class GunController {

    @Autowired
    private TestService service;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public List<Test> selectTableTest() {
        return service.selectAll();
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Test selectTest(@PathVariable("id") long testId) {
        return service.selectOne(testId);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Test insertAndUpdateTest(@RequestBody Test test) {
        return service.insertAndUpdate(test);
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteTest(@PathVariable("id") long testId) {
        service.delete(testId);
    }

}
