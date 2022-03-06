package codegym.controller;

import codegym.model.Human;
import codegym.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/humans")
public class HumanController {
    @Autowired
    private IHumanService humanService;

    @GetMapping
    public ResponseEntity<Iterable<Human>> showAll() {
        Iterable<Human> humans = humanService.findAll();
        if (!humans.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(humans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Human> showOne(@PathVariable Long id) {
        Optional<Human> humanOptional = humanService.findById(id);
        return humanOptional.map(human -> new ResponseEntity<>(human, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Human> createHuman(@RequestBody Human human) {
        Human humanCreate = humanService.save(human);
        return new ResponseEntity<>(humanCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Human> updateHuman(@RequestBody Human human, @PathVariable Long id) {
        Optional<Human> humanOptional = humanService.findById(id);
        if (!humanOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        human.setId(humanOptional.get().getId());
        return new ResponseEntity<>(humanService.save(human), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Human> delete(@PathVariable Long id) {
        Optional<Human> humanOptional = humanService.findById(id);
        if (!humanOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        humanService.remove(id);
        return new ResponseEntity<>(humanOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Human>> findAllByName(@RequestParam String search) {
        Iterable<Human> humans = humanService.findAllByName(search);
        if (!humans.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(humans, HttpStatus.OK);
    }
}
