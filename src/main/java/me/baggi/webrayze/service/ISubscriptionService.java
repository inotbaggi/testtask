package me.baggi.webrayze.service;

import me.baggi.webrayze.exception.subscription.SubscriptionNotFoundException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.model.Subscription;
import me.baggi.webrayze.request.AddSubscriptionRequest;

import java.util.List;

public interface ISubscriptionService {
    Subscription addSubscription(long userId, AddSubscriptionRequest request) throws UserNotFoundException;

    List<Subscription> getUserSubscriptions(long userId) throws UserNotFoundException;

    void removeUserSubscription(long userId, long subscriptionId) throws UserNotFoundException, SubscriptionNotFoundException;

    List<String> getTopSubscriptions();
}
