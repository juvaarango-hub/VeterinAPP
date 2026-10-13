package app.repository;

import app.domain.Tutor;
import app.service.outputports.TutorRepository;

import java.util.ArrayList;
import java.util.List;

public class TutorRepositoryImplCollection implements TutorRepository {

    private final List<Tutor> tutors = new ArrayList<>();


    @Override
    public Tutor save(Tutor tutor) {
        if (tutor == null) {
            throw new IllegalArgumentException("El tutor no puede ser null.");
        }

        if (findById(tutor.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un tutor con esa identificación.");
        }

        tutors.add(tutor);
        return tutor;
    }


    @Override
    public List<Tutor> findAll() {
        return new ArrayList<>(tutors);
    }

    @Override
    public Tutor findById(int id) {
        for (Tutor tutor : tutors) {
            if (tutor.getId() == id) {
                return tutor;
            }
        }

        return null;
    }

    @Override
    public Tutor update(Tutor tutor) {
        if (tutor == null) {
            throw new IllegalArgumentException("El tutor no puede ser null.");
        }

        for (int i = 0; i < tutors.size(); i++) {
            if (tutors.get(i).getId() == tutor.getId()) {
                tutors.set(i, tutor);
                return tutor;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return tutors.removeIf(tutor -> tutor.getId() == id);
    }
}

