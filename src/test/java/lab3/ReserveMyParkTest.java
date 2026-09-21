package lab3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ReserveMyParkTest {

    private static final double DELTA = 0.001;
    private final ReserveMyPark calculator = new ReserveMyPark();

    // ---------- Equivalence Partitioning ----------

    @Test
    @DisplayName("TC-01 (EP-TC1): child, Arkansas resident, non-veteran -> $115.00")
    void tc01_childResident() throws ReservationException {
        assertEquals(115.00, calculator.calculateStayPrice(5, 8, true, false), DELTA);
    }

    @Test
    @DisplayName("TC-02 (EP-TC2): adult, non-resident, veteran -> $225.00")
    void tc02_adultVeteran() throws ReservationException {
        assertEquals(225.00, calculator.calculateStayPrice(5, 30, false, true), DELTA);
    }

    @Test
    @DisplayName("TC-03 (EP-TC3): senior, non-resident, non-veteran -> $200.00")
    void tc03_senior() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 70, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-04 (EP-TC4): nights = 0 throws NightReservationException")
    void tc04_nightsBelowMinimum() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(0, 30, false, false));
    }

    @Test
    @DisplayName("TC-05 (EP-TC5): nights = 20 throws NightReservationException")
    void tc05_nightsAboveMaximum() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(20, 30, false, false));
    }

    @Test
    @DisplayName("TC-06 (EP-TC6): age = -5 throws GuestAgeReservationException")
    void tc06_negativeAge() {
        assertThrows(GuestAgeReservationException.class,
                () -> calculator.calculateStayPrice(5, -5, false, false));
    }

    @Test
    @DisplayName("TC-07 (EP-TC7): nights = 0 and age = -5 throws NightReservationException")
    void tc07_invalidNightsAndInvalidAge() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(0, -5, false, false));
    }

    // ---------- Boundary Value Analysis: nights ----------

    @Test
    @DisplayName("TC-08 (BV-TC2): nights = 1 (min) -> $50.00")
    void tc08_nightsMin() throws ReservationException {
        assertEquals(50.00, calculator.calculateStayPrice(1, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-09 (BV-TC3): nights = 2 (min + 1) -> $100.00")
    void tc09_nightsMinPlusOne() throws ReservationException {
        assertEquals(100.00, calculator.calculateStayPrice(2, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-10 (BV-TC4): nights = 5 (nominal), no discounts -> $250.00")
    void tc10_nightsNominal() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-11 (BV-TC5): nights = 13 (max - 1) -> $650.00")
    void tc11_nightsMaxMinusOne() throws ReservationException {
        assertEquals(650.00, calculator.calculateStayPrice(13, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-12 (BV-TC6): nights = 14 (max) -> $700.00")
    void tc12_nightsMax() throws ReservationException {
        assertEquals(700.00, calculator.calculateStayPrice(14, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-13 (BV-TC7): nights = 15 (max + 1) throws NightReservationException")
    void tc13_nightsMaxPlusOne() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(15, 30, false, false));
    }

    // ---------- Boundary Value Analysis: guest age ----------

    @Test
    @DisplayName("TC-14 (BV-TC8): age = -1 throws GuestAgeReservationException")
    void tc14_ageMinusOne() {
        assertThrows(GuestAgeReservationException.class,
                () -> calculator.calculateStayPrice(5, -1, false, false));
    }

    @Test
    @DisplayName("TC-15 (BV-TC9): age = 0 (child, min) -> $125.00")
    void tc15_ageZero() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 0, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-16 (BV-TC10): age = 1 (child, min + 1) -> $125.00")
    void tc16_ageOne() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 1, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-17 (BV-TC11): age = 11 (child, max - 1) -> $125.00")
    void tc17_ageEleven() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 11, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-18 (BV-TC12): age = 12 (child, max) -> $125.00")
    void tc18_ageTwelve() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 12, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-19 (BV-TC13): age = 13 (adult, min) -> $250.00")
    void tc19_ageThirteen() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 13, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-20 (BV-TC14): age = 14 (adult, min + 1) -> $250.00")
    void tc20_ageFourteen() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 14, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-21 (BV-TC15): age = 63 (adult, max - 1) -> $250.00")
    void tc21_ageSixtyThree() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 63, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-22 (BV-TC16): age = 64 (adult, max) -> $250.00")
    void tc22_ageSixtyFour() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 64, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-23 (BV-TC17): age = 65 (senior, min) -> $200.00")
    void tc23_ageSixtyFive() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 65, false, false), DELTA);
    }

    @Test
    @DisplayName("TC-24 (BV-TC18): age = 66 (senior, min + 1) -> $200.00")
    void tc24_ageSixtySix() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 66, false, false), DELTA);
    }

    // ---------- Decision Table ----------

    @Test
    @DisplayName("TC-25 (DT-TC2): Arkansas resident only -> $240.00")
    void tc25_residentOnly() throws ReservationException {
        assertEquals(240.00, calculator.calculateStayPrice(5, 30, true, false), DELTA);
    }

    @Test
    @DisplayName("TC-26 (DT-TC4): resident AND veteran -> $216.00")
    void tc26_residentAndVeteran() throws ReservationException {
        assertEquals(216.00, calculator.calculateStayPrice(5, 30, true, true), DELTA);
    }

    @Test
    @DisplayName("TC-27 (DT-TC5): child, veteran only -> $112.50")
    void tc27_childVeteran() throws ReservationException {
        assertEquals(112.50, calculator.calculateStayPrice(5, 8, false, true), DELTA);
    }

    @Test
    @DisplayName("TC-28 (DT-TC6): child, resident AND veteran -> $103.50")
    void tc28_childResidentAndVeteran() throws ReservationException {
        assertEquals(103.50, calculator.calculateStayPrice(5, 8, true, true), DELTA);
    }

    @Test
    @DisplayName("TC-29 (DT-TC7): senior, resident AND veteran -> $171.00")
    void tc29_seniorResidentAndVeteran() throws ReservationException {
        assertEquals(171.00, calculator.calculateStayPrice(5, 70, true, true), DELTA);
    }
}
