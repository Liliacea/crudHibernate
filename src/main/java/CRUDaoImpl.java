import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CRUDaoImpl implements CRUDao<Student, Integer> {

    SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
    Session session = sessionFactory.openSession();

    public CRUDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public Student add(Student student) {
        try  {
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
        return student;
    }

    @Override
    public List<Student> findById(Integer id) {
        List<Student> students = null;
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("id", id));
        students = criteria.list();
        for (Student student : students) {
            System.out.println(student.toString());


        }
        return students;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public Student delete(Student student) {
        return null;
    }
    @Override
    public void close() throws Exception {

    }
}
