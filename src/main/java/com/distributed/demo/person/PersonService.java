package com.distributed.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        boolean exist = personRepository.existsById(personId);
        if (!exist) {
            throw new IllegalStateException("Person with id " + personId + " does not exists");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, String name, String lastname) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "Person with id " + personId + " does not exists"
                ));
        if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
            person.setName(name);
        }
        if (lastname != null && lastname.length() > 0 && !Objects.equals(person.getLastname(), lastname)) {
            person.setLastname(lastname);
        }
    }
}
