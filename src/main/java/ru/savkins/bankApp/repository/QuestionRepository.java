package ru.savkins.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import ru.savkins.bankApp.model.Question;


@Repository()
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
