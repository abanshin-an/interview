package ru.interview.lesson7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    public static final String STUDENT_FORM = "student_form";
    public static final String STUDENT = "student";

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("minAgeFilter") Optional<String> minAgeFilter,
                           @RequestParam("maxAgeFilter") Optional<String> maxAgeFilter
    ) {
        Specification<Student> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec=spec.and(StudentSpecification.nameLike(nameFilter.get()));
        }
        if (minAgeFilter.isPresent() && !minAgeFilter.get().isBlank()) {
            spec=spec.and(StudentSpecification.minAgeFilter(Integer.parseInt(minAgeFilter.get())));
        }
        if (maxAgeFilter.isPresent() && !maxAgeFilter.get().isBlank()) {
            spec=spec.and(StudentSpecification.maxAgeFilter(Integer.parseInt(maxAgeFilter.get())));
        }
        List<Student> students = studentRepository.findAll(spec);
        model.addAttribute("students", students);
        return STUDENT;
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute(STUDENT, studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found")));
        return STUDENT_FORM;
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute(STUDENT, new Student());
        return STUDENT_FORM;
    }

    @PostMapping
    public String save(Student student, BindingResult result) {
        //@Valid
        if (result.hasErrors()) {
            return STUDENT_FORM;
        }
        studentRepository.save(student);
        return "redirect:/student";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        studentRepository.deleteById(id);
        return "redirect:/student";
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}

