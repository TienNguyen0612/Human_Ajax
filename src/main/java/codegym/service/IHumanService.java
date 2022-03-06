package codegym.service;

import codegym.model.Human;

import java.util.Optional;

public interface IHumanService {
    Iterable<Human> findAll();

    Optional<Human> findById(Long id);

    Human save(Human human);

    void remove(Long id);

    Iterable<Human> findAllByName(String name);
}
