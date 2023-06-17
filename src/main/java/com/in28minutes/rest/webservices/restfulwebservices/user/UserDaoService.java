package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import javax.sql.rowset.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class UserDaoService {
    // use JPA or Hibernate to talk to database ... will use static arraylist for now
    //find all users
    // save a user
    //find on user by id

    private static List<User> users = new ArrayList<>();
    private static int usersCount=0;

    static {
        users.add(new User(++usersCount,"Marco", LocalDate.now().minusYears(28)));
        users.add(new User(++usersCount,"Kelsie", LocalDate.now().minusYears(27)));
        users.add(new User(++usersCount,"Anson", LocalDate.now().minusYears(3)));
        users.add(new User(++usersCount,"Emmersyn", LocalDate.now().minusYears(1)));
    }

    public List<User> findAll(){
        return users;
    }
    public User findUser(int id) {

       for(User user: users){
           if(user.getId() == (id)){
               return user;
           }
       }
       return null;
    }

    public User findUserWithStream(int id){
        return users.stream().filter(user ->user.getId() == id).findFirst().orElse(null);
    }
    public void deleteUserWithStream(int id){
         users.removeIf(user -> user.getId() == id);
    }

    public User saveUser(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User deleteUser(int id){
        for(User user: users){
            if(user.getId() == id){
                users.remove(user);
            }
        }
        return null;
    }
}
