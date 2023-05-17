import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.function.Function;

public class CRUDaoImpl implements CRUDao<Student, Integer> {

    SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
    Session session = sessionFactory.openSession();


    public CRUDaoImpl(Session session) {
        this.session = session;
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
/* Session session = sessionFactory.getCurrentSession();
        employee.setEmployeeId(session.get(Employee.class,employee.getEmployeeId()).getEmployeeId());
        session.update(employee);
* */
      //  Session currentSession = sessionFactory.getCurrentSession();
        student.setId((Integer) session.get(Student.class, student.getId()));
        student.setName("masha");
        return tx(session -> {

            session.update(student);
            return student;
        });
    }


    private <T> T tx(final Function<Session, T> command) {
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

        } finally {
            session.close();
        }
    }
       /* try  {
            session.beginTransaction();
           // String[] returnId = {"id"};
            String sql = "INSERT INTO public.students (id, surname, name, dateOfBirth) VALUES (:id, :surname,:name,:dateOfBirth) ";
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setParameter("id", student.getId());
            sqlQuery.setParameter("surname",student.getSurname());
            sqlQuery.setParameter("name",student.getName());
            sqlQuery.setParameter("dateOfBirth",student.getDateOfBirth());
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return student;*/


    @Override
    public List<Student> findById(Integer id) {
        try {
            Session session = HibernateRunner.getSessionFactory().openSession();
            session.beginTransaction();
            List<Student> students = null;
            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("id", id));
            students = criteria.list();
            for (Student student : students) {
                System.out.println(student.toString());


            }
            return students;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            session.close();
            throw e;
        }
    }


    @Override
    public Student delete(Student student) {
        return null;
    }
    @Override
    public void close() throws Exception {

    }
}
