package com.farhad.ChallengeApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ChallengeRepo extends JpaRepository<Challenge, Long> {

    Optional<Challenge> findByMonthIgnoreCase(String month);
}
