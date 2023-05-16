import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

class CRUDaoImplTest {
    // CRUDaoImpl cruDaoImpl = new CRUDaoImpl(HibernateRunner.getSessionFactory().openSession());
     // CRUDaoImpl cruDaoImpl = new CRUDaoImpl((Session) HibernateRollBack.create(HibernateRunner.getSessionFactory()));
    SessionFactory factory = HibernateRollBack.create(HibernateRunner.getSessionFactory());
    Session session = factory.openSession();
    CRUDaoImpl cruDaoImpl = new CRUDaoImpl(session);

@Test
    void add() {
        Student ivanov = new Student.Builder()
                .id(1)
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();



        assertThat(cruDaoImpl.add(ivanov).getName(), is("ivan"));

    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}