package th.seffka.gun.server.service;

import th.seffka.gun.server.entity.Test;

import java.util.List;

public interface TestService {

    List<Test> selectAll();
    Test selectOne(long id);
    Test insertAndUpdate(Test test);
    void delete(long id);

}
