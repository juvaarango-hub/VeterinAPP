package app.service.inputports;

import app.domain.ClinicHistory;
import java.util.List;

public interface ClinicHistoryService {
    ClinicHistory registerClinicHistory(ClinicHistory clinicHistory);
    List<ClinicHistory> getAllClinicHistories();
    ClinicHistory getClinicHistoryById(int appointmentId);
    ClinicHistory updateClinicHistory(ClinicHistory clinicHistory);
    boolean existsById(int appointmentId);
    boolean deleteClinicHistory(int appointmentId);

    List<ClinicHistory> getClinicHistoriesByPetId(int petId);
}
