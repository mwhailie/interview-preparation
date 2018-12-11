package edu.neu.companies.quora;

public class BitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        while(i < bits.length - 1){
            if(bits[i] == 0){
                i ++;
            }else{
                i += 2;
            }
        }
        return i == bits.length - 1;
    }
}
