package ferramong.pay.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super("Unauthorized access");
    }
}
