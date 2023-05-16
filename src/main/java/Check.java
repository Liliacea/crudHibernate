import java.sql.Date;
import java.time.LocalDate;

public class Check {
    public static void main(String[] args) {
        CRUDaoImpl cruDaoImpl = new CRUDaoImpl(HibernateRunner.getSessionFactory().openSession());
       // SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
      //  Session session = sessionFactory.openSession();
        Student ivanov = new Student.Builder()
                .id(1)
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        cruDaoImpl.findById(1);


    }
}
