package app.service.outputports;

import app.domain.ClinicHistory;
import java.util.List;

public interface ClinicHistoryRepository {
    ClinicHistory save(ClinicHistory clinicHistory);
    List<ClinicHistory> findAll();
    ClinicHistory findById(int appointmentId);
    ClinicHistory update(ClinicHistory clinicHistory);
    boolean deleteById(int appointmentId);
}