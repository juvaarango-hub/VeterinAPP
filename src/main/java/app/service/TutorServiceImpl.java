package app.service;

import app.domain.Tutor;
import app.service.inputports.TutorService;
import app.service.outputports.TutorRepository;
import app.service.validators.TutorValidator;

import java.util.List;

public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final TutorValidator tutorValidator;

    public TutorServiceImpl(
            TutorRepository tutorRepository,
            TutorValidator tutorValidator) {
        this.tutorRepository = tutorRepository;
        this.tutorValidator = tutorValidator;
    }

    @Override
    public Tutor registerTutor(Tutor tutor) {
        tutorValidator.validate(tutor);
        return tutorRepository.save(tutor);
    }

    @Override
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor getTutorById(int id) {
        return tutorRepository.findById(id);
    }

    @Override
    public Tutor updateTutor(Tutor tutor) {
        tutorValidator.validate(tutor);
        return tutorRepository.update(tutor);
    }

    @Override
    public boolean existsById(int id) {
        return tutorRepository.findById(id) != null;
    }

    @Override
    public boolean deleteTutor(int id) {
        return tutorRepository.deleteById(id);
    }
}
