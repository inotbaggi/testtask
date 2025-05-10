package me.baggi.webrayze.exception.subscription;

public class SubscriptionAlreadyExistException extends Exception{
    @Override
    public String getMessage() {
        return "Subscription already exist";
    }
}
