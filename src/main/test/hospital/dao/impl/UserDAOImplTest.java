package hospital.dao.impl;

import hospital.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    @Test
    public void findAll() {
    }

    @Test
    public void findByEmailAndPass() {
        User activeUser = UserDAOImpl.findByEmailAndPass("stevemadden@gmail.com","b9f42a213cef73f57b5c22bc0f612c8386121e6409febc6e6070a1b8dd19669b");
        assertNotNull(activeUser);
    }

    @Test
    public void insert() {

        User user = new User("Liza","Onyshchenko","lemonycap@gmail.com","16af147fef7eaa61cd113fe1e8902176ae89cfbe8d0808f0ca7b3627bc0fddf0",2);
        UserDAOImpl.insert(user);
    }

}