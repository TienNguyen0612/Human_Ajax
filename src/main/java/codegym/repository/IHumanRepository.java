package codegym.repository;

import codegym.model.Human;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHumanRepository extends CrudRepository<Human, Long> {
    Iterable<Human> findAllByNameContaining(String name);
}
