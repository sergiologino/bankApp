package ru.savkins.bankApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.savkins.bankApp.exception.ResourceNotFoundException;
import ru.savkins.bankApp.model.Answer;
import ru.savkins.bankApp.repository.AnswerRepository;
import ru.savkins.bankApp.repository.QuestionRepository;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(@PathVariable Long questionId,
                            @Valid @RequestBody Answer answer) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepository.save(answer);
                }).orElseThrow(()->new ResourceNotFoundException("Question not found with id"));
    }

    @PutMapping("/question/{questionId}/answers/{answerId}")
    public Answer updateAnswer(@PathVariable Long questionId,
                               @PathVariable Long answerId,
                               @Valid @RequestBody Answer answerRequest) throws ResourceNotFoundException {
        if(!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Answer not found with id"+ questionId);
        }
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(()-> new ResourceNotFoundException("Answer not found with id"+answerId));
    }
    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,
                                          @PathVariable Long answerId) throws ResourceNotFoundException {
        if(!questionRepository.existsById(questionId)){
            throw new ResourceNotFoundException("Qestion not found with id "+questionId);
        }
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                    }).orElseThrow(()-> new ResourceNotFoundException("Answer not found with id "+answerId));
    }
}
