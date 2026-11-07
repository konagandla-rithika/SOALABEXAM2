package com.example.task;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

   
    @Test
    void shouldSaveAndFindTaskById() {
        Task task = new Task("Write report", "Finish quarterly report", false);

        Task saved = taskRepository.save(task);
        System.out.println("Generated ID: " + saved.getId());

       
        assertThat(saved.getId()).isNotNull();

       
        Optional<Task> found = taskRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Write Report");
        assertThat(found.get().getDescription()).isEqualTo("Finish quarterly report");
        assertThat(found.get().isCompleted()).isFalse();
       
    }
}
