import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
@Entity
@Table(name = "public.students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    public Student() {
    }

    private Student (Builder builder) {
    //    id = builder.id;
        surname = builder.surname;
        name = builder.name;
        dateOfBirth = builder.dateOfBirth;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static class Builder {

        private String surname;


        private String name;
        private Date dateOfBirth;


        public Builder() {


        }

        public Builder surname (String val) {
            surname = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder dateOfBirth(Date val) {
            dateOfBirth = val;
            return this;
        }


        //Метод с возвращающим типом Good для генерации объекта
        public Student build() {
            return new Student(this);
        }


    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
