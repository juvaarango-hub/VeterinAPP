package app.service.inputports;

import app.domain.Pets;
import java.util.List;

public interface PetsService {

    Pets registerPet(Pets pet);

    List<Pets> getAllPets();

    Pets getPetById(int id);

    Pets updatePet(Pets pet);

    boolean existsById(int id);

    boolean deletePet(int id);
}