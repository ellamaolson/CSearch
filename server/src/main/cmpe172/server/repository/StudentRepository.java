package server.repository;

import server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByName(String text);
//    List<Student> findAll();
    Student findById(int id);
//    boolean delete(int id);
}
