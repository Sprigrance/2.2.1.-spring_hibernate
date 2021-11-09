package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Иван", "Петров", "ivan@mail.ru");
        User user2 = new User("Ирина", "Иванова", "irina@mail.ru");
        User user3 = new User("Сергей", "Сидоров", "sergey@mail.ru");
        User user4 = new User("Мария", "Петрова", "mariya@mail.ru");

        Car car1 = new Car("Peugeout", 207);
        Car car2 = new Car("Mazda", 6);
        Car car3 = new Car("VAZ", 2114);
        Car car4 = new Car("BMW", 5);

        user1.setUserCar(car1);
        user2.setUserCar(car2);
        user3.setUserCar(car3);
        user4.setUserCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar().getModel() + " " +
                    user.getUserCar().getSeries());
            System.out.println();
        }

        User ownerOfCar = userService.getUserByCarModelAndSeries("Mazda", 6);
        System.out.println(ownerOfCar.getFirstName() + " " + ownerOfCar.getLastName());
        context.close();
    }
}
