import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.function.Function;
public class CRUDaoImpl implements CRUDao<Student,Integer> {
    SessionFactory sessionFactory;

    public CRUDaoImpl(SessionFactory factory) {
        this.sessionFactory = factory;
    }


    public <T> T tx(Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
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
        return tx(session -> {

            session.update(student);
            return student;
        });
    }

    @Override
    public List<Student> findById(Integer id) {
        return tx(session -> {
            List<Student> students = null;
            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("id", id));
            students = criteria.list();

            return students;
        });
    }

    @Override
    public Student delete(Student student) {
        return tx(session -> {

            session.delete(student);
            return student;
        });
    }
}

