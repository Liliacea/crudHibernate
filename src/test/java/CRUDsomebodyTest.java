import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CRUDsomebodyTest {
    CRUDsomebody cruDsomebody = new CRUDsomebody(HibernateRunner.getSessionFactory().openSession());


    @Test
    void add() {
        Student ivanov = new Student.Builder()
                .id(1)
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();


        assertThat(cruDsomebody.add(ivanov).getName(), is("ivan"));

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