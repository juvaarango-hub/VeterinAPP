package app.service.inputports;

import app.domain.Tutor;
import java.util.List;

public interface TutorService {

    Tutor registerTutor(Tutor tutor);

    List<Tutor> getAllTutors();

    Tutor getTutorById(int id);

    Tutor updateTutor(Tutor tutor);

    boolean existsById(int id);

    boolean deleteTutor(int id);

}
