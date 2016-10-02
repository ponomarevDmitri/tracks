package ru.analteam.gtracks.exception;

/**
 * Created by dima-pc on 01.10.2016.
 */
public class AccessToRouteDenied extends Exception {

    public AccessToRouteDenied(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessToRouteDenied(String message) {
        super(message);
    }

    public AccessToRouteDenied() {
    }

}
