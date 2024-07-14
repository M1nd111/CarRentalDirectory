//package spring.ws.carrentaldirectoryweb.core.service;
//
//import com.querydsl.jpa.impl.JPAQuery;
//
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//
//import lombok.ToString;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import spring.ws.carrentaldirectoryweb.core.repository.UserRepository;
//import spring.ws.carrentaldirectoryweb.core.entity.User;
//import spring.ws.carrentaldirectoryweb.core.mapper.UserCreateMapper;
//import spring.ws.carrentaldirectoryweb.core.mapper.UserReadMapper;
//import spring.ws.carrentaldirectoryweb.core.dto.QPredicates;
//import spring.ws.carrentaldirectoryweb.core.dto.UserCreateDto;
//import spring.ws.carrentaldirectoryweb.core.dto.UserFilter;
//import spring.ws.carrentaldirectoryweb.core.dto.UserReadDto;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static  spring.ws.carrentaldirectoryweb.core.entity.QUser.user;
//
//@RequiredArgsConstructor
//@ToString
//@Service
//@Transactional(readOnly = true)
//public class UserService {
//    private final UserRepository userRepository;
//    private final UserCreateMapper userCreateMapper;
//    private final UserReadMapper userReadMapper;
//    private final EntityManager entityManager;
//
//
//    @Transactional
//    public UserReadDto save(UserCreateDto entity) {
//        System.out.println(entity);
//        User user = userCreateMapper.map(entity);
//
//
//        userRepository.save(user);
//        return userReadMapper.map(user);
//    }
//
//
//    @Transactional
//    public boolean delete(Long id) {
//        userRepository.delete(userRepository.findById(id).get());
//        userRepository.flush();
//        return true;
//    }
//    @Transactional
//    public UserReadDto update(Integer id, UserCreateDto entity) {
//        User user = userCreateMapper.map(entity);
//        user.setId(id);
//
//        entityManager.merge(user);
//        entityManager.flush();
//        return userReadMapper.map(user);
//    }
//
//    public Optional<UserReadDto> findById(Long id) {
//        return userRepository.findById(id).map(userReadMapper::map);
//    }
//
//    public List<UserReadDto> findAll() {
//        return userRepository.findAll().stream().map(userReadMapper::map).collect(Collectors.toList());
//    }
//
//    public List<UserReadDto> findAll(UserFilter filter) {
//        List<User> users = findAllByFilter(filter);
//        return users.stream()
//                .map(userReadMapper::map)
//                .collect(Collectors.toList());
//    }
//
//    public List<User> findAllByFilter(UserFilter filter) {
//
//        var predicate = QPredicates.builder()
//                .add(filter.firstname(), user.firstname::containsIgnoreCase)
//                .add(filter.lastname(), user.lastname::containsIgnoreCase)
//                .buildAnd();
//
//        return new JPAQuery<User>(entityManager).select(user).from(user).where(predicate).fetch();
//    }
//
//    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
//
//        var predicate = QPredicates.builder()
//                .add(filter.firstname(), user.firstname::containsIgnoreCase)
//                .add(filter.lastname(), user.lastname::containsIgnoreCase)
//                .buildAnd();
//
//        return userRepository.findAll(predicate, pageable).map(userReadMapper::map);
//    }
//
//}
//
