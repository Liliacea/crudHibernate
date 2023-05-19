import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.function.Function;

public class CRUDaoImpl implements CRUDao<Student,Integer> {

    SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
    Session session = sessionFactory.openSession();


    public CRUDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public   <T> T tx(Function<Session, T> command) {
        try {
            Session session = HibernateRunner.getSessionFactory().openSession();
            final Transaction tx = session.beginTransaction();
            try {
                T rsl = command.apply(session);
                tx.commit();
                return rsl;

            } catch (final Exception e) {
                session.getTransaction().rollback();

                throw e;

            }
        } catch (final Exception e) {
            session.getTransaction().rollback();

            throw e;
        }


    }

     @Override
    public Student add(Student student) {
        return tx(session -> {
            session.save(student);
            return student;
        });
    }
    @Override
    public Student update(Student student) {


        return tx(session-> {
       /*  int id=1;
            Query query = session.createQuery("FROM User where name = 'ivan'");
            List<Student> students = query.list();
            for (Student student1 : students) {
             id = student1.getId();
            }

        */

                student.setId(student.getId());
                student.setName("masha");
               session.update(student);

            return student;


        });
    }
    @Override
    public List<Student> findById(Integer id) {
     /*   try {
            Session session = HibernateRunner.getSessionFactory().openSession();
            session.beginTransaction();
            {
                Query query = session.createQuery("FROM public.students where id = id");
                List<Student> students = query.list();
                for (Student student : students) {
                    System.out.println(student.toString());
                }
                return students;
            }
        } catch (final Exception e) {
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }

      */

        return tx(session-> {
            List<Student> students = null;
            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("id", id));
            students = criteria.list();
            for (Student student : students) {
                System.out.println(student.toString());


            }
            return students;
        });
        }





    @Override
    public Student delete(Student student) {
        return tx(session-> {
            student.setId(student.getId());//



            session.delete(student);

            return student;


        });
    }
    @Override
    public void close() throws Exception {
        session.close();
    }


}
