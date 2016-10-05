package th.seffka.gun.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import th.seffka.gun.server.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}
