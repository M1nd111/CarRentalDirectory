package spring.ws.carrentaldirectoryweb.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import spring.ws.carrentaldirectoryweb.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , QuerydslPredicateExecutor<User> {

    Page<User> findAllBy(Pageable pageable);

    void deleteAll();

    User findByName(String name);

    User findByApplicationNumber(Long number);


}



