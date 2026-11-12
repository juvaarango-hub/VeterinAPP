package app.service;

import app.domain.ClinicHistory;
import app.service.inputports.ClinicHistoryService;
import app.service.outputports.ClinicHistoryRepository;
import app.service.validators.ClinicHistoryValidator;

import java.util.List;

public class ClinicHistoryServiceImpl implements ClinicHistoryService {

    private final ClinicHistoryRepository clinicHistoryRepository;
    private final ClinicHistoryValidator clinicHistoryValidator;

    public ClinicHistoryServiceImpl(ClinicHistoryRepository clinicHistoryRepository,
                                    ClinicHistoryValidator clinicHistoryValidator) {
        this.clinicHistoryRepository = clinicHistoryRepository;
        this.clinicHistoryValidator = clinicHistoryValidator;
    }

    @Override
    public ClinicHistory registerClinicHistory(ClinicHistory clinicHistory) {
        clinicHistoryValidator.validate(clinicHistory);
        if (existsById((int) clinicHistory.getAppointment().getIdAppointment())) {
            throw new IllegalArgumentException("Ya existe una historia clínica para esta cita.");
        }
        return clinicHistoryRepository.save(clinicHistory);
    }

    @Override
    public List<ClinicHistory> getAllClinicHistories() {
        return clinicHistoryRepository.findAll();
    }

    @Override
    public ClinicHistory getClinicHistoryById(int appointmentId) {
        return clinicHistoryRepository.findById(appointmentId);
    }

    @Override
    public ClinicHistory updateClinicHistory(ClinicHistory clinicHistory) {
        clinicHistoryValidator.validate(clinicHistory);
        return clinicHistoryRepository.update(clinicHistory);
    }

    @Override
    public boolean existsById(int appointmentId) {
        return clinicHistoryRepository.findById(appointmentId) != null;
    }

    @Override
    public boolean deleteClinicHistory(int appointmentId) {
        return clinicHistoryRepository.deleteById(appointmentId);
    }

    @Override
    public List<ClinicHistory> getClinicHistoriesByPetId(int petId) {
        return List.of();
    }
}
