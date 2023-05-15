import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CRUDsomebody implements CRUDrepository<Student, Integer>{

    SessionFactory sessionFactory = HibernateRunner.getSessionFactory();
    Session session = sessionFactory.openSession();

    public CRUDsomebody(Session session) {
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
    public Student findById(Integer id) {
        return null;
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
