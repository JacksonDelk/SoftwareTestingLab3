package lab3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for ReserveMyPark.calculateStayPrice(...).
 *
 * Test case IDs (EP-TC*, BV-TC*, DT-TC*) correspond to the Final Test Suite.
 * Each test is traceable back to the Equivalence Partition (EP), Boundary
 * Value (BV), or Decision Table (DT) items it covers.
 */
public class ReserveMyParkTest {

    private static final double DELTA = 0.001;
    private final ReserveMyPark calculator = new ReserveMyPark();

    // ---------- Equivalence Partitioning ----------

    @Test
    @DisplayName("EP-TC1: child, Arkansas resident, non-veteran -> $115.00")
    void epTc1_childResident() throws ReservationException {
        assertEquals(115.00, calculator.calculateStayPrice(5, 8, true, false), DELTA);
    }

    @Test
    @DisplayName("EP-TC2: adult, non-resident, veteran -> $225.00")
    void epTc2_adultVeteran() throws ReservationException {
        assertEquals(225.00, calculator.calculateStayPrice(5, 30, false, true), DELTA);
    }

    @Test
    @DisplayName("EP-TC3: senior, non-resident, non-veteran -> $200.00")
    void epTc3_senior() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 70, false, false), DELTA);
    }

    @Test
    @DisplayName("EP-TC4: nights = 0 throws NightReservationException")
    void epTc4_nightsBelowMinimum() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(0, 30, false, false));
    }

    @Test
    @DisplayName("EP-TC5: nights = 20 throws NightReservationException")
    void epTc5_nightsAboveMaximum() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(20, 30, false, false));
    }

    @Test
    @DisplayName("EP-TC6: age = -5 throws GuestAgeReservationException")
    void epTc6_negativeAge() {
        assertThrows(GuestAgeReservationException.class,
                () -> calculator.calculateStayPrice(5, -5, false, false));
    }

    // ---------- Boundary Value Analysis: nights ----------

    @Test
    @DisplayName("BV-TC2: nights = 1 (min) -> $50.00")
    void bvTc2_nightsMin() throws ReservationException {
        assertEquals(50.00, calculator.calculateStayPrice(1, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC3: nights = 2 (min + 1) -> $100.00")
    void bvTc3_nightsMinPlusOne() throws ReservationException {
        assertEquals(100.00, calculator.calculateStayPrice(2, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC4: nights = 5 (nominal), no discounts -> $250.00")
    void bvTc4_nightsNominal() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC5: nights = 13 (max - 1) -> $650.00")
    void bvTc5_nightsMaxMinusOne() throws ReservationException {
        assertEquals(650.00, calculator.calculateStayPrice(13, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC6: nights = 14 (max) -> $700.00")
    void bvTc6_nightsMax() throws ReservationException {
        assertEquals(700.00, calculator.calculateStayPrice(14, 30, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC7: nights = 15 (max + 1) throws NightReservationException")
    void bvTc7_nightsMaxPlusOne() {
        assertThrows(NightReservationException.class,
                () -> calculator.calculateStayPrice(15, 30, false, false));
    }

    // ---------- Boundary Value Analysis: guest age ----------

    @Test
    @DisplayName("BV-TC8: age = -1 throws GuestAgeReservationException")
    void bvTc8_ageMinusOne() {
        assertThrows(GuestAgeReservationException.class,
                () -> calculator.calculateStayPrice(5, -1, false, false));
    }

    @Test
    @DisplayName("BV-TC9: age = 0 (child, min) -> $125.00")
    void bvTc9_ageZero() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 0, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC10: age = 1 (child, min + 1) -> $125.00")
    void bvTc10_ageOne() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 1, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC11: age = 12 (child, max) -> $125.00")
    void bvTc11_ageTwelve() throws ReservationException {
        assertEquals(125.00, calculator.calculateStayPrice(5, 12, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC12: age = 13 (adult, min) -> $250.00")
    void bvTc12_ageThirteen() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 13, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC13: age = 14 (adult, min + 1) -> $250.00")
    void bvTc13_ageFourteen() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 14, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC14: age = 64 (adult, max) -> $250.00")
    void bvTc14_ageSixtyFour() throws ReservationException {
        assertEquals(250.00, calculator.calculateStayPrice(5, 64, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC15: age = 65 (senior, min) -> $200.00")
    void bvTc15_ageSixtyFive() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 65, false, false), DELTA);
    }

    @Test
    @DisplayName("BV-TC16: age = 66 (senior, min + 1) -> $200.00")
    void bvTc16_ageSixtySix() throws ReservationException {
        assertEquals(200.00, calculator.calculateStayPrice(5, 66, false, false), DELTA);
    }

    // ---------- Decision Table ----------

    @Test
    @DisplayName("DT-TC2: Arkansas resident only -> $240.00")
    void dtTc2_residentOnly() throws ReservationException {
        assertEquals(240.00, calculator.calculateStayPrice(5, 30, true, false), DELTA);
    }

    @Test
    @DisplayName("DT-TC4: resident AND veteran -> $216.00")
    void dtTc4_residentAndVeteran() throws ReservationException {
        assertEquals(216.00, calculator.calculateStayPrice(5, 30, true, true), DELTA);
    }
}
