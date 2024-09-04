package com.farhad.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
	

    @Autowired
    private ChallengeRepo challengeRepo;
	
    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;



//    public ChallengeService(){
//        this.challengeRepo = challengeRepo;
////        Challenge challenge1 = new Challenge(1L, "January", "Learn a new programming language");
////        challenges.add(challenge1);
//    }

    public List<Challenge> getAllChallenges(){
        return challengeRepo.findAll();
    }

    public boolean addChallenge(Challenge challenge){

        if(challenge != null){
            challenge.setId(nextId++);
            challengeRepo.save(challenge);
            return true;
        }

        return false;
    }

//    public Challenge getChallenge(Long id) {
//        for(Challenge challenge: challenges){
//            if(challenge.getId().equals(id)){
//                return challenge;
//            }
//        }
//        return null;
//    }

    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = challengeRepo.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    public boolean updateChallenge(Long id, Challenge updatedChallenge) {
        Optional<Challenge> challenge = challengeRepo.findById(id);
        if(challenge.isPresent()){
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());
            challengeRepo.save(challengeToUpdate);
            return true;
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge = challengeRepo.findById(id);
        if(challenge.isPresent()){
            challengeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
