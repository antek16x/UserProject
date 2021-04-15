import api.UserRepository;
import impl.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Logger logger = new LoggerImpl();
//        logger.setName("LoggerUserRepository");
//        logger.setVersion("1.3b");
//        UserRepository userRepository = new UserRepositoryImpl(logger);

        ApplicationContext context
                = new ClassPathXmlApplicationContext("config2.xml");
        UserRepository userRepository = context.getBean(UserRepository.class);

        User ola = new User("olenka", "ola", "kowalska");
        User kasia = new User("kasienka", "kasia", "nowak");
        User tomek = new User("tomus", "tomek", "tomkowski");

        userRepository.create(kasia);
        userRepository.create(ola);
        userRepository.create(tomek);

        UserRepository userRepository1 = context.getBean(UserRepository.class);
        userRepository1.create(new User("ala", "ala", "alala"));

        userRepository.update(new User("michas", "michalek", "kowalski"));
        userRepository.update(new User("michas", "michal", "michalowski"));

        userRepository.delete("adas");
        if (userRepository.exists("adas")) {
            System.out.println("Adas is in repository");
        } else {
            System.out.println("Adas isn't in repository");
        }

        System.out.println("\nList of users in rep:");
        List<User> list = userRepository.findAll();
        for (User i : list) {
            System.out.println(i);
        }

        System.out.println("\nList of users in repository 1:");
        List<User> list1 = userRepository1.findAll();
        for (User i : list1) {
            System.out.println(i);
        }


    }
}
