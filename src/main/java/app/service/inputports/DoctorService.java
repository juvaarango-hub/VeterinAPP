package app.service.inputports;

import app.domain.Doctor;
import java.util.List;


public interface DoctorService {

    Doctor registerDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(int id);
    Doctor updateDoctor(Doctor doctor);
    boolean existsById(int id);
    boolean deleteDoctor(int id);
}
