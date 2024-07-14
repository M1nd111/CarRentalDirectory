//package spring.ws.carrentaldirectoryweb.core.mapper;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import spring.ws.carrentaldirectoryweb.core.entity.User;
//import spring.ws.carrentaldirectoryweb.core.dto.UserCreateDto;
//
//@Component
//@RequiredArgsConstructor
//public class UserCreateMapper implements Mapper<UserCreateDto, User> {
//
//    @Override
//    public User map(UserCreateDto object) {
//        User user = new User();
//        copy(object, user);
//        return user;
//    }
//
//    public User map(UserCreateDto object, User user) {
//        copy(object, user);
//        return user;
//    }
//
//    private void copy(UserCreateDto object, User user) {
//        user.setTime(object.getTime());
//        user.setFirstname(object.getFirstname());
//        user.setName(object.getName());
//        user.setLastname(object.getLastname());
//        user.setMarkName(object.getMarkName());
//        user.setApplicationNumber(object.getApplicationNumber());
//
//    }
//}
//
