package edu.neu.companies.goldsman;

import java.util.List;

public class Pair {
    static long playlist(List<Integer> songs) {
        long ans = 0L;
        if(songs == null || songs.size() <= 1){
            return ans;
        }
        int[] frequency = new int[60];
        for(int song : songs){
            frequency[song % 60]++;
        }

        ans += frequency[0] * (frequency[0] - 1)/2;
        ans += frequency[30] * (frequency[30] - 1)/2;

        for(int second = 1; second < 30; second ++){
            ans += frequency[second] * frequency[60 - second];
        }
        return ans;
    }
}
