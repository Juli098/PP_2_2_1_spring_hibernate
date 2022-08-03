package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Vasya", "Vasechkin", "Vasechkin@mail.ru", new Car("Volvo", 7)));
      userService.add(new User("Olga", "Petrova", "Petrova@mail.ru", new Car("Ford", 2)));
      userService.add(new User("Sasha", "Sergeev", "Sergeev@mail.ru", new Car("BMW", 5)));
      userService.add(new User("Sveta", "Popova", "Popova@mail.ru", new Car("Mazda", 3)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar().getModel());
         System.out.println("Series = "+ user.getCar().getSeries());
         System.out.println();
      }
       System.out.println(userService.getUserByCar("BMW", 5));



      context.close();
   }
}
