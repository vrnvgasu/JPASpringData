package ru.edu;

import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.edu.dao.entity.StudentEntity;
import ru.edu.dao.repo.StudentRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // берем бины для контекста из пакета ru.edu
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.edu");
        StudentRepository repository = context.getBean(StudentRepository.class);
        System.out.println( "DONE" );

        //////////////////
        StudentEntity student2 = new StudentEntity();
        student2.setId("2");
        student2.setFirstName("Ivan");
        student2.setLastName("Ivanov");
        student2.setCourse(3);

        repository.save(student2);

        //////////////////
        List<StudentEntity> students = repository.findAll();
        System.out.println("students: " + students);

        //////////////////
        Optional<StudentEntity> optionalStudent2 = repository.findById("2");
        System.out.println("user with id 2: " + optionalStudent2.orElse(null));
        Optional<StudentEntity> optionalStudent3 = repository.findById("3");
        System.out.println("user with id 3: " + optionalStudent3.orElse(null));

        //////////////////
        repository.deleteById("2");
        System.out.println("students after delete: " + repository.findAll());

    }
}
