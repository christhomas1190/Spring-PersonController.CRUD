import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {

    public PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository)
    {this.personRepository=personRepository;}

    @GetMapping
    @ResponseBody
    public Iterable<Person> getAllPersons() {
        return this.personRepository.findAll();}

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> createUser(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }
    @PutMapping
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson){
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }
    @GetMapping("/{id}")
    //for a single person
    public Person getOnePersonbyId(@PathVariable Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        personRepository.deleteById(id);
    }
}

