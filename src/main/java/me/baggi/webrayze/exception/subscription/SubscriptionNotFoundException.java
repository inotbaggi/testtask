package me.baggi.webrayze.exception.subscription;

public class SubscriptionNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Subscription not found";
    }
}
