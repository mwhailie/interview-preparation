package edu.neu.companies.draftkings;

import java.util.*;

public class Game {

    /*
           {100, [3,5,6]}
           |
           V
           {97, [1]}
           |
           V
           {95, [8->]}

           TreeMap<Integer, Set<Integer>> bucket
     */
    class LineUp{
        int lineUpId;
        int totalScore;
        Set<Integer> athletes;
    }

    class User{
        int user_id;
        LineUp lineUp;
        public User(int user_id){
            this.user_id = user_id;
        }
    }

    Map<Integer, User> users;
    Map<Integer, LineUp> lineUps;
    PriorityQueue<Integer> pq;

    public Game() {
        users = new HashMap<>();
        lineUps = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> (users.get(a).lineUp).totalScore - (users.get(b).lineUp).totalScore) ;
    }

    void registerUser(int user_id, int lineUpId){
        User u = new User(user_id);
        u.lineUp = lineUps.get(lineUpId);
        users.put(user_id, u);

        pq.remove(user_id);
        pq.offer(user_id);
        if(pq.size() > N){
            pq.poll();
        }
    }

    void updateAthlete(int player_id, int score){
        for(int user_id : users.keySet()){
            LineUp lu = users.get(user_id).lineUp;
            if(lu.athletes.contains(player_id)){
                lu.totalScore += score;

                pq.remove(user_id);
                pq.offer(user_id);
                if(pq.size() > N){
                    pq.poll();
                }
            }
        }
    }
    int N;
    List<Integer> rank(int N){
        return (List<Integer>) pq.iterator();
    }
    //O(Nlogk)
    List<Integer> rankII(int N){
        return (List<Integer>) pq.iterator();
    }

    public class LiveScoreTracker
    {
        /// <summary>
/// A lineup owned by a single user.  You are welcome to add to this class, if necessary!
/// Assume getters and setters for all properties, including any you create.
/// </summary>
        public class LineUp
        {
            public int rank;
            public int TotalScore;
            public Set<Integer> AthleteIds;
        }
        // Exercise: Implement the following methods, along with any supporting functionality.
        /// <summary>
        /// Called when a user registers a lineup. This will not be called after the contest begins.
        /// </summary>
        /// <param name="userId">The ID of the user registering a lineup.  A user may register more than one lineup.</param>
        /// <param name="lineup">A single lineup object.</param>

        //Map key userId val List<lineup>
        Map<Integer, List<LineUp>> users;
        List<LineUp> list ;

        public LiveScoreTracker(){
            users = new HashMap<>();
            list = new ArrayList<>();
        }
        public void AddUserLineup(int userId, LineUp lineup)
        {
            if(!users.containsKey(userId)){
                users.put(userId, new ArrayList<>());
//            bestScore.put(userId, 0);
            }
            list.add(lineup);
            users.get(userId).add(lineup);
        }

        /// <summary>
        /// Called when an athlete's fantasy score is changed.
        /// </summary>
        /// <param name="athleteId">The ID of the athlete who had a scoring event.</param>
        /// <param name="scoreChange">The incremental change in score for the athlete.</param>
        public void RegisterAthleteScore(int athleteId, int scoreChange)
        {
            for(int userId : users.keySet()){
                List<LineUp> lineUps = users.get(userId);
                for(LineUp lineUp : lineUps){
                    if(lineUps.contains(athleteId)){
                        lineUp.TotalScore += scoreChange;
                    }
                }
            }
            Collections.sort(list, (a, b) -> b.TotalScore - a.TotalScore);
            int rank = 1;
            for(int i = 0; i < list.size(); i++){
                if(i == 0 || list.get(i).TotalScore == list.get(i - 1).TotalScore){
                    list.get(i).rank = rank;
                }else{
                    list.get(i).rank = i + 1;
                }
            }
        }
        // O(N) N num of lineUp
        // n = total # of users
        // m = avg # of lineups per user
        // O(nm)
        // O()
/*

    The following questions are very important to our users during the game:
        * How is my best lineup doing?
        * What are the best lineups in the contest and how are they doing?

    To make the LiveScoreTracker more exciting, let's add two more methods:
        * The GetBestCurrentUserScore method returns the best total score belonging to a single user's best lineup
        * The GetLeaderboard method returns the globally highest-ranked list of lineups across all users.
    ** We expect GetLeaderboard will be called very frequently.

        *******************************
        * Add an integer property named Rank to the Lineup class and set it according to "Standard competition ranking".
        *******************************
        * In standard competition ranking, if A ranks ahead of B and C (which compare equal) which are both ranked ahead of D, then     A gets ranking number 1 ("first"), B gets ranking number 2 ("joint second"), C also gets ranking number 2 ("joint second")     and D gets ranking number 4 ("fourth").

        ----
    Example leader board:
    User Name | Players in lineup | Rank | Total Score
    Jessie    | P1, P2, ...       | 1    | 193
    Ellen     | P9, P7, ...       | 2    | 188
    Mike      | P5, P7, ...       | 2    | 188
    Andy      | P6, P12, ...      | 4    | 168
    Jessie    | P1, P12, ...      | 5    | 158
        ----


 i ts
 0 193 1
 1 188 2
 2 188 2
 3 168 3

*/

        /// <summary>
        /// Gets the best total score for a single user's best lineup.
        /// </summary>
        /// <param name="userId">The ID of the user.</param>
        /// <returns>The score total in points.</returns>
        public int GetBestCurrentUserScore(int userId)
        {

            List<LineUp> lineUps = users.get(userId);
            int max = Integer.MIN_VALUE;
            for(LineUp lineUp : lineUps ){
                max = Math.max(max, lineUp.TotalScore);
            }
            return max;
        }


        //PriorityQueue lineUp TotalScore
        // offer
        //PriorityQueue size > numberPositions
        // poll
        /// <summary>
        /// Gets the top N lineups globally across all users being tracked.
        /// Lineups returned by this function should have Rank set according to standard competition ranking.
        /// </summary>
        /// <param name="numberPositions">The number of top N positions to return.</param>
        /// <returns>A global list of lineups ordered by TotalScore, decending.</returns>
        public List<LineUp> GetLeaderboard(int numberPositions)
        {
            // Collections.sort(list, (a, b) -> b.TotalScore - a.TotalScore);

            return list.subList(0, numberPositions);
        }

        //O(nm*log(nm))
    }
}
/*
    A group of Users compete with each other by building virtual Lineups made up of real athletes.
        By tracking the real world performance of the athletes in the various Lineups, we can rank those Lineups against each other.
        Lineups earn points when an athlete in that lineup scores in the real world.

        Implement the following LiveScoreTracker class, with these assumptions:

        * Before the games start, a series of users will call AddUserLineup to set their lineups
        * A user may register more than one lineup; there is no way to remove a lineup
        * Once the competitions start, AddUserLineup will no longer be called

        * As the real world games proceed RegisterAthleteScore will be called with the single score adjustment for that athlete when they score.
        ** The identifier AthleteId represents a single real-world NFL athlete.

*/



