import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.stat.SessionStatistics;

import java.io.Serializable;
import java.sql.Connection;

public class HibernateRunner {





    private static SessionFactory sessionFactory = null;

    static {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());

        sessionFactory = configuration.buildSessionFactory(builder.build());

    }
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }



}





