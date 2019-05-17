package com.ipiecoles.java.java350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void getNombreAnneeAncienneteNow(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now());

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNminus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().minusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(2, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNull(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(null);

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @Test
    public void getNombreAnneeAncienneteNplus2(){
        //Given
        Employe e = new Employe();
        e.setDateEmbauche(LocalDate.now().plusYears(2L));

        //When
        Integer anneeAnciennete = e.getNombreAnneeAnciennete();

        //Then
        Assertions.assertEquals(0, anneeAnciennete.intValue());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'T12345', 0, 1.0, 1000.0",
            "1, 'T12345', 2, 0.5, 600.0",
            "1, 'T12345', 2, 1.0, 1200.0",
            "2, 'T12345', 0, 1.0, 2300.0",
            "2, 'T12345', 1, 1.0, 2400.0",
            "1, 'M12345', 0, 1.0, 1700.0",
            "1, 'M12345', 5, 1.0, 2200.0",
            "2, 'M12345', 0, 1.0, 1700.0",
            "2, 'M12345', 8, 1.0, 2500.0"
    })
    public void getPrimeAnnuelle(Integer performance, String matricule, Long nbYearsAnciennete, Double tempsPartiel, Double primeAnnuelle){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbYearsAnciennete), Entreprise.SALAIRE_BASE, performance, tempsPartiel);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertEquals(primeAnnuelle, prime);

    }

    @Test
    public void checkAugmenterSalaireNullPercent() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            employe.augmenterSalaire(null);
        } );
    }

    @Test
    public void checkAugmenterSalaireNegativePercent() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0);
         Assertions.assertThrows(IllegalArgumentException.class, () -> {
            employe.augmenterSalaire((double) -1);
        } );
    }

    @Test
    public void checkAugmenterSalaireNullSalaire() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), null, 1, 1.0);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            employe.augmenterSalaire((double) 10);
        } );
    }

    @Test
    public void checkAugmenterSalaireNegativeSalaire() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), (double) -1, 1, 1.0);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            employe.augmenterSalaire((double) 10);
        } );
    }

    @Test
    public void checkAugmenterSalaireZeroPercent() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            employe.augmenterSalaire((double) 0);
        } );
    }

    @Test
    public void checkAugmenterSalaire10Percent() {
        Employe employe = new Employe("Norimaki", "Arale", "M00001", LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0);
        employe.augmenterSalaire(10.0);
        Double salaireAttendu = 1673.342;
        Assertions.assertEquals(employe.getSalaire(), salaireAttendu);
    }

}