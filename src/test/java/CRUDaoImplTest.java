
import org.junit.jupiter.api.Test;


import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CRUDaoImplTest {
    CRUDaoImpl cruDaoImpl = new CRUDaoImpl(HibernateRollBack.create(HibernateRunner.getSessionFactory()));
    @Test
    void add() {
        Student ivanov = new Student.Builder()
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        assertThat(cruDaoImpl.add(ivanov).getName(), is("ivan"));
    }
    @Test
    void findById() {
        Student ivanov = new Student.Builder()
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        cruDaoImpl.add(ivanov);
        assertThat(cruDaoImpl.findById(ivanov.getId()).size(), is(1) );
    }
    @Test
    void update() {
        Student ivanov = new Student.Builder()
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        cruDaoImpl.add(ivanov);
        assertThat(cruDaoImpl.update(ivanov).getName(),is("masha"));
    }
    @Test
    void delete() {
        Student ivanov = new Student.Builder()
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        cruDaoImpl.add(ivanov);
        cruDaoImpl.delete(ivanov);
       assertThat(cruDaoImpl.findById(ivanov.getId()).size(), is(0));
    }
}