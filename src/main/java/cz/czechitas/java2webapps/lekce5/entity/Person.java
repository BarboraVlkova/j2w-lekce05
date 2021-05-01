package cz.czechitas.java2webapps.lekce5.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// Entita reprezentujici osobu.
// jsou to FIELDY, ktere jsou ve sloupečcích
public class Person {
 private String givenName;
 private String lastName;
 private LocalDate birthDate;

 // prázdný konstruktor
 public Person() {
 }

 // konstruktor se všemi fieldy
 public Person(String givenName, String lastName, LocalDate birthDate) {
  this.givenName = givenName;
  this.lastName = lastName;
  this.birthDate = birthDate;
 }

 // gettery a settery
 public String getGivenName() {
  return givenName;
 }

 public void setGivenName(String givenName) {
  this.givenName = givenName;
 }

 public String getLastName() {
  return lastName;
 }

 public void setLastName(String lastName) {
  this.lastName = lastName;
 }

 public LocalDate getBirthDate() {
  return birthDate;
 }


 // je to vlastnost Property a dám ho nad gettery i settery, teď ho dáme nad settery
 // anotace DateFormat a očekávám datum v ISO formě
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 public void setBirthDate(LocalDate birthDate) {
  this.birthDate = birthDate;
 }
}
