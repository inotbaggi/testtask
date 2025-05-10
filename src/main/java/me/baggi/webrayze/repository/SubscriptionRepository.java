package me.baggi.webrayze.repository;

import me.baggi.webrayze.model.Subscription;
import me.baggi.webrayze.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s.type FROM Subscription s GROUP BY s.type ORDER BY COUNT(s) DESC LIMIT :limit")
    List<String> findTopSubscriptionTypes(@Param("limit") int limit);

    List<Subscription> findAllByOwner(User owner);
}
