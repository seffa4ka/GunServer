package th.seffka.gun.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.seffka.gun.server.entity.Test;
import th.seffka.gun.server.repository.TestRepository;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository repository;

    public List<Test> selectAll() {
        return repository.findAll();
    }

    public Test selectOne(long id) {
        return repository.findOne(id);
    }

    public Test insertAndUpdate(Test test) {
        return repository.saveAndFlush(test);
    }

    public void delete(long id) {
        repository.delete(id);
    }
}
