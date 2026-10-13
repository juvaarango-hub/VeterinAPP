package app.service;

import app.domain.Doctor;
import app.service.inputports.DoctorService;
import app.service.outputports.DoctorRepository;
import app.service.validators.DoctorValidator;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorValidator doctorValidator;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorValidator doctorValidator) {
        this.doctorRepository = doctorRepository;
        this.doctorValidator = doctorValidator;
    }

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        doctorValidator.validate(doctor);
        return doctorRepository.save(doctor);
    }



    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        doctorValidator.validate(doctor);
        return doctorRepository.update(doctor);
    }

    @Override
    public boolean existsById(int id) {
        return doctorRepository.findById(id) != null;
    }

    @Override
    public boolean deleteDoctor(int id) {
        return doctorRepository.deleteById(id);
    }

}
