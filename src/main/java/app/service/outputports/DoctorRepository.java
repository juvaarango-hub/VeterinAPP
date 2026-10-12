package app.service.outputports;

import app.domain.Doctor;
import java.util.List;

public interface DoctorRepository {
    Doctor save(Doctor doctor);
    List<Doctor> findAll();
    Doctor findById(int id);
    Doctor update(Doctor doctor);
    boolean deleteById(int id);
}
