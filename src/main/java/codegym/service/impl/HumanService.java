package codegym.service.impl;

import codegym.model.Human;
import codegym.repository.IHumanRepository;
import codegym.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumanService implements IHumanService {
    @Autowired
    private IHumanRepository iHumanRepository;

    @Override
    public Iterable<Human> findAll() {
        return iHumanRepository.findAll();
    }

    @Override
    public Optional<Human> findById(Long id) {
        return iHumanRepository.findById(id);
    }

    @Override
    public Human save(Human human) {
        return iHumanRepository.save(human);
    }

    @Override
    public void remove(Long id) {
        iHumanRepository.deleteById(id);
    }

    @Override
    public Iterable<Human> findAllByName(String name) {
        return iHumanRepository.findAllByNameContaining(name);
    }
}
