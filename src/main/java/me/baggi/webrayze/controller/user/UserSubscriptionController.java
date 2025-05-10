package me.baggi.webrayze.controller.user;

import me.baggi.webrayze.dto.SubscriptionDTO;
import me.baggi.webrayze.exception.subscription.SubscriptionNotFoundException;
import me.baggi.webrayze.exception.user.UserNotFoundException;
import me.baggi.webrayze.mapper.SubscriptionMapper;
import me.baggi.webrayze.model.Subscription;
import me.baggi.webrayze.request.AddSubscriptionRequest;
import me.baggi.webrayze.service.ISubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/subscriptions")
public class UserSubscriptionController {
    private final ISubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    public UserSubscriptionController(ISubscriptionService subscriptionService, SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getSubscriptions(
            @PathVariable long id
    ) throws UserNotFoundException {
        List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(id);
        List<SubscriptionDTO> subscriptionDTOS = subscriptionMapper.toDTO(subscriptions);
        return ResponseEntity.ok(subscriptionDTOS);
    }

    @PostMapping
    public ResponseEntity<SubscriptionDTO> addSubscription(
            @PathVariable long id,
            @RequestBody AddSubscriptionRequest body
    ) throws UserNotFoundException {
        Subscription subscription = subscriptionService.addSubscription(id, body);
        SubscriptionDTO subscriptionDTO = subscriptionMapper.toDTO(subscription);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Boolean> deleteSubscription(
            @PathVariable long id,
            @PathVariable long subId
    ) throws UserNotFoundException, SubscriptionNotFoundException {
        subscriptionService.removeUserSubscription(id, subId);
        return ResponseEntity.ok(true);
    }
}
