package app.service;

import app.domain.Pets;
import app.service.inputports.PetsService;
import app.service.outputports.PetsRepository;

import java.util.List;

public class PetsServiceImpl implements PetsService {

    private final PetsRepository petsRepository;
    private int nextId = 1;

    public PetsServiceImpl(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    @Override
    public Pets registerPet(Pets pet) {
        if (pet == null) {
            throw new IllegalArgumentException(
                    "La mascota no puede ser null."
            );
        }

        pet.setId(nextId);
        Pets registeredPet = petsRepository.save(pet);
        nextId++;

        return registeredPet;
    }

    @Override
    public List<Pets> getAllPets() {
        return petsRepository.findAll();
    }

    @Override
    public Pets getPetById(int id) {
        return petsRepository.findById(id);
    }

    @Override
    public Pets updatePet(Pets pet) {
        return petsRepository.update(pet);
    }

    @Override
    public boolean existsById(int id) {
        return petsRepository.findById(id) != null;
    }

    @Override
    public boolean deletePet(int id) {
        return petsRepository.deleteById(id);
    }
}
