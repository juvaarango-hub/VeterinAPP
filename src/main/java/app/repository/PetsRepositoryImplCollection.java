package app.repository;

import app.domain.Pets;
import app.service.outputports.PetsRepository;

import java.util.ArrayList;
import java.util.List;

public class PetsRepositoryImplCollection implements PetsRepository {

    private final List<Pets> pets = new ArrayList<>();

    @Override
    public Pets save(Pets pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La mascota no puede ser null.");
        }

        if (findById(pet.getId()) != null) {
            throw new IllegalArgumentException("Ya existe una mascota con ese ID.");
        }

        pets.add(pet);
        return pet;
    }

    @Override
    public List<Pets> findAll() {
        return new ArrayList<>(pets);
    }

    @Override
    public Pets findById(int id) {
        for (Pets pet : pets) {
            if (pet.getId() == id) {
                return pet;
            }
        }

        return null;
    }

    @Override
    public Pets update(Pets pet) {
        if (pet == null) {
            throw new IllegalArgumentException("La mascota no puede ser null.");
        }

        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getId() == pet.getId()) {
                pets.set(i, pet);
                return pet;
            }
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return pets.removeIf(pet -> pet.getId() == id);
    }
}