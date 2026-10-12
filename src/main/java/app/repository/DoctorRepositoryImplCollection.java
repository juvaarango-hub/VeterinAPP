package app.repository;

import app.domain.Doctor;
import app.service.outputports.DoctorRepository;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImplCollection implements DoctorRepository {

    private final List<Doctor> doctors = new ArrayList<>();

    @Override
    public Doctor save(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("El doctor no puede ser null.");
        }
        if (findById(doctor.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un doctor con esa identificación.");
        }
        doctors.add(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }

    @Override
    public Doctor findById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public Doctor update(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("El doctor no puede ser null.");
        }
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == doctor.getId()) {
                doctors.set(i, doctor);
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return doctors.removeIf(doctor -> doctor.getId() == id);
    }
}
