package me.baggi.webrayze.service.impl;

import me.baggi.webrayze.exception.subscription.SubscriptionNotFoundException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.model.Subscription;
import me.baggi.webrayze.model.User;
import me.baggi.webrayze.repository.SubscriptionRepository;
import me.baggi.webrayze.request.AddSubscriptionRequest;
import me.baggi.webrayze.service.ISubscriptionService;
import me.baggi.webrayze.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {
    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private final IUserService userService;
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(IUserService userService, SubscriptionRepository subscriptionRepository) {
        this.userService = userService;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public Subscription addSubscription(long userId, AddSubscriptionRequest request) throws UserNotFoundException {
        User user = userService.getUser(userId);
        if (user == null) {
            logger.warn("Attempt to add subscription to user! User not found by id: {}", userId);
            throw new UserNotFoundException();
        }

        Subscription subscription = new Subscription();
        subscription.setType(request.getType());
        subscription.setOwner(user);

        subscriptionRepository.save(subscription);
        logger.info("Added subscription({}) to user({})", subscription.getId(), user.getId());

        return null;
    }

    @Override
    public List<Subscription> getUserSubscriptions(long userId) throws UserNotFoundException {
        User user = userService.getUser(userId);
        if (user == null) {
            logger.warn("Attempt to get user subscriptions! User not found by id: {}", userId);
            throw new UserNotFoundException();
        }

        return subscriptionRepository.findAllByOwner(user);
    }

    @Override
    public void removeUserSubscription(long userId, long subscriptionId) throws SubscriptionNotFoundException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
        if (subscription == null) {
            logger.warn("Attempt to remove user subscription! Subscription not found by id: {}", userId);
            throw new SubscriptionNotFoundException();
        }

        subscriptionRepository.deleteById(subscriptionId);
        logger.info("Removed subscription({}) from user({})", subscription.getId(), userId);
    }

    @Override
    public List<String> getTopSubscriptions() {
        return subscriptionRepository.findTopSubscriptionTypes(3);
    }
}
