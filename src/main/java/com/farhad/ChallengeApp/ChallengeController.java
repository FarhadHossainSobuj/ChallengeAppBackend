package com.farhad.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

//    private ChallengeService challengeService = new ChallengeService();
    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public List<Challenge> getAllChallenges(){
        return challengeService.getAllChallenges();
    }

    @PostMapping
    public List<Challenge> addChallenge(@RequestBody Challenge challenge){
        System.out.println(challenge.getId());
        System.out.println(challenge.getMonth());
        System.out.println(challenge.getDescription());
        challengeService.addChallenge(challenge);
        return challengeService.getAllChallenges();
    }

//    @GetMapping("/challenges/{id}")
//    public Challenge getChallenge(@PathVariable Long id){
//
//        Challenge challenge = challengeService.getChallenge(id);
//
//        if(challenge != null)
//            return challenge;
//        else
//            return null;
//    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        Challenge challenge = challengeService.getChallenge(month);

        if(challenge != null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge){
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedChallenge);
        if(isChallengeUpdated)
            return new ResponseEntity<>("Challenge updated succesfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Challenge updated failed!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isChallengeUpdated = challengeService.deleteChallenge(id);
        if(isChallengeUpdated)
            return new ResponseEntity<>("Challenge deleted succesfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>("Challenge deleted failed!", HttpStatus.NOT_FOUND);
    }
}
