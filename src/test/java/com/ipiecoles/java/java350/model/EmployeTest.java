package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

public class EmployeTest {

    private Employe testEmploye;

    public EmployeTest() {
        testEmploye = new Employe();
    }

    @Test
    public void testCheckSeniorityYearNullDate() {
        // given (dE: null)
        testEmploye.setDateEmbauche(null);
        // expected output: 0
        Assertions.assertThat(testEmploye.getNombreAnneeAnciennete()).isEqualTo(0);
    }

    @Test
    public void testCheckSeniorityYearFutureDate() {
        // given (dE: 2 ans apres now)
        LocalDate inputDate = LocalDate.now().plusYears((long) 2);
        // expected output: 0
        Assertions.assertThat(testEmploye.getNombreAnneeAnciennete()).isEqualTo(0);
    }

    @Test
    public void testCheckSeniorityYearSameYear() {
        // given (dE: meme annee que now)
        LocalDate inputDate = LocalDate.now();
        testEmploye.setDateEmbauche(inputDate);
        // expected output: 0
        Assertions.assertThat(testEmploye.getNombreAnneeAnciennete()).isEqualTo(0);
    }

    @Test
    public void testCheckSeniorityYearNormalPastDate() {
        // given (dE: 2ans avant now)
        LocalDate inputDate = LocalDate.now().minusYears((long) 2);
        testEmploye.setDateEmbauche(inputDate);
        // expected ouput: 2
        Assertions.assertThat(testEmploye.getNombreAnneeAnciennete()).isEqualTo(2);
    }
}
