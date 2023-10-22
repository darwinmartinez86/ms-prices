package es.com.between.exception;

public class PricesNotFoundException extends RuntimeException {

    public PricesNotFoundException(String message) {
        super(message);
    }

}
