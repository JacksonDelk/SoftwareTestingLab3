package lab3;

public class NightReservationException extends ReservationException {
    public NightReservationException(int nights) {
        super("An invalid number of nights was selected: " + nights);
    }
}
