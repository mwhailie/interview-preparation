package edu.neu.companies.celo;

import java.util.*;

public class BlockChainII {
    class Block{
        String blockHash;
        String prevBlockHash;
        int nonce;
        String blockTranscation;

        public Block(String prevBlockHash, String blockTranscation){
            this.prevBlockHash = prevBlockHash;
            this.blockTranscation = blockTranscation;
            this.nonce = 0;
            this.blockHash = sha1(prevBlockHash + ", " , nonce ,", " + blockTranscation);
            while(!blockHash.startsWith("0000")){
                blockHash = sha1(prevBlockHash + ", " , (++nonce) , ", " + blockTranscation);
            }
        }

        @Override
        public String toString() {
            return blockHash + ", " + prevBlockHash + ", " + nonce + ", " + blockTranscation;
        }
    }
    String getLatestBlock(int[] startBalances, int[][] pendingTransactions, int blockSize){

        int N = pendingTransactions.length;
        String prevBlockHash = "0000000000000000000000000000000000000000";
        Block latestBlock = null;
        boolean invalid = false;
        int blockStart = 0;
        for(; blockStart + blockSize <= N; ){
            int i = 0;
            for(; i < blockSize; i ++){
                int[] pendingTransaction = pendingTransactions[blockStart + i];
                int fromAddress = pendingTransaction[0];
                int toAddress = pendingTransaction[1];
                int value = pendingTransaction[2];
                if(startBalances[fromAddress] >= value){
                    startBalances[fromAddress] -= value;
                    startBalances[toAddress] += value;
                }else{
                    invalid = true;
                    break;
                }
            }
            int[][] blockTransaction = Arrays.copyOfRange(pendingTransactions, blockStart, blockStart + i);
            latestBlock = new Block(prevBlockHash, Arrays.deepToString(blockTransaction));
            if(invalid){
                break;
            }
            prevBlockHash = latestBlock.blockHash;
            blockStart = blockStart + i;
        }
        if(blockStart < N && !invalid){
            int[][] blockTransaction = Arrays.copyOfRange(pendingTransactions, blockStart, N);
            latestBlock = new Block(prevBlockHash, Arrays.deepToString(blockTransaction));
        }
        return latestBlock.toString();
    }

    String sha1(String text, int nonce, String after) {
        String sha1 = "";
        try
        {
            java.security.MessageDigest crypt = java.security.MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(text.getBytes("UTF-8"));
            crypt.update((byte) nonce);
            crypt.update(after.getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for (byte b : crypt.digest()) {
                formatter.format("%02x", b);
            }
            sha1 = formatter.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    public static void main(String[] args) {
        BlockChain bc = new BlockChain();
        System.out.println( bc.getLatestBlock(new int[]{5, 0, 0}, new int[][]{{0, 1, 5}, {1, 2, 5}},2));
        System.out.println( bc.getLatestBlock(new int[]{3, 10, 10, 3}, new int[][]{{3,2,2}, {2,3,5},{3,2,4}, {3,0,2}, {1,2,2}},2));


    }
}
