import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.time.LocalDate;

public class Check {
    public static void main(String[] args) {
        CRUDsomebody cruDsomebody = new CRUDsomebody(HibernateRunner.getSessionFactory().openSession());
       // SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
      //  Session session = sessionFactory.openSession();
        Student ivanov = new Student.Builder()
                .id(1)
                .surname("ivanov")
                .name("ivan")
                .dateOfBirth(Date.valueOf(LocalDate.now()))
                .build();
        cruDsomebody.add(ivanov);


    }
}
