package cz.czechitas.java2webapps.lekce5.controller;

import cz.czechitas.java2webapps.lekce5.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Filip Jirsák
 */
@Controller
public class FamousPeopleController {
  private final List<Person> people;

  public FamousPeopleController() {
    people = new ArrayList<>();
    people.add(new Person("Hermiona","Granger", LocalDate.of(1979,9,19)));
    people.add(new Person("Ronald","Weasley", LocalDate.of(1980,3,1)));
    people.add(new Person("Severus","Snape", LocalDate.of(1960,1,9)));
  }

  @GetMapping("/")
  public ModelAndView list() {
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", people);
    return result;
  }

  // params = metoda vyžaduje tyto parametry
  // query = v prohlížeči je v adrese q
  // vezmu seznam, má se postupně procházet, filtrovat / vyfiltrovaný seznam chci zpátky předělat na List
  // r.47 => mám dotaz, pridám nový prvek do modelu
  @GetMapping(value = "/", params = "query")
  public ModelAndView query(String query) {
    List<Person> filteredPeople = people.stream()
            .filter(person -> person.getGivenName().contains(query) || person.getLastName().contains(query))
            .collect(Collectors.toList());
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", filteredPeople);
    result.addObject("query", query);
    return result;
  }

  // metoda, která vrací úvodní stránku v prohlížeči
  @PostMapping(value = "/", params = {"givenName", "lastName", "birthDate"})
  public String append(Person person) {
    people.add(person);

    return "redirect:/";
  }

// redirect = přesměrování stránky: vložená adresa, kam se má přesměrovat
  @PostMapping(value = "/", params = {"id"})
  public String delete(int id) {
    people.remove(id);
    return "redirect:/";
  }
}
