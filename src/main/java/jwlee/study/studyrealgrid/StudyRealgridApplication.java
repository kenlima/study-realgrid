package jwlee.study.studyrealgrid;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

@SpringBootApplication
@RestController
public class StudyRealgridApplication {


    private List<User> users = new ArrayList<>();


    public static void main(String[] args) {
        SpringApplication.run(StudyRealgridApplication.class, args);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }

    @Bean
    public CommandLineRunner runner() throws Exception {
        return (a) -> {
            Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 1);
//            List<Integer> collect = infiniteStream.limit(10).collect(Collectors.toList());
            users = infiniteStream.limit(100000)
                    .map(i -> makeUser(i)).collect(Collectors.toList());


            users.forEach(System.out::println);

/*
            User user = new User();
            user.setId("kenlima");
            user.setName("이정우");
            user.setPhone("010-6659-5045");
            users.add(user);*/
        };
    }

    private User makeUser(Integer no) {
        User user = new User();
        user.setNo(no);
        user.setId("kenlima-" + no);
        user.setName("이정우-" + no);
        user.setPhone("010-6659-5045");
        return user;
    }

}

@Data
class User {
    private Integer no;
    private String id;
    private String name;
    private String phone;

}
