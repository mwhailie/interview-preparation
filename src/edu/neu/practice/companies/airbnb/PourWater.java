package edu.neu.practice.companies.airbnb;

public class PourWater {
    public void showLandScape(int[] heights, int[] afterDrop){
        int row = 0;
        for(int height:heights){
            row = Math.max(row, height);
        }
        for(int i = row; i >= 0; i --){
            System.out.println();
            for(int j = 0; j < heights.length; j ++){
                if(heights[j] >= i) {
                    System.out.print("*");
                }else if(afterDrop[j] >= i){
                    System.out.print("W");
                }else{
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
    public int maxDrop(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int total = 0;
        int[] maxOnLeft = new int[height.length];
        maxOnLeft[0] = height[0];
        for(int i = 1; i < height.length; i ++){
            maxOnLeft[i] = Math.max(height[i], maxOnLeft[i - 1]);
        }
        int[] maxOnRight = new int[height.length];
        maxOnRight[height.length - 1] = height[height.length - 1];
        for(int i = height.length - 2; i >=0 ; i --){
            maxOnRight[i] = Math.max(height[i], maxOnRight[i + 1]);
        }
        // maxOnLeft  [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
        // maxOnRight [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
        for(int i = 0; i < height.length; i ++){
            total += (Math.min(maxOnRight[i], maxOnLeft[i]) - height[i]);
        }
//        System.out.println(total);
        return total;
    }

    public int[] dropRain(int[] beforeDrop, int numOfRainDrop, int position){
        int[] afterDrop = beforeDrop.clone();
        showLandScape(beforeDrop, afterDrop);
        numOfRainDrop = Math.max(maxDrop(beforeDrop), numOfRainDrop);
        while(numOfRainDrop -- > 0){
            int index = position;
            for(int i = position - 1; i >= 0; i --){
                if(afterDrop[i] > afterDrop[index]){
                    break;
                }else if(afterDrop[i] < afterDrop[index]){
                    index = i;
                }
            }
            if(index != position){
                afterDrop[index] ++;;
                continue;
            }
            for(int i = position + 1; i < afterDrop.length; i ++){
                if(afterDrop[i] > afterDrop[index]){
                    break;
                }else if(afterDrop[i] < afterDrop[index]){
                    index = i;
                }
            }
            afterDrop[index] ++;
        }
        showLandScape(beforeDrop, afterDrop);
        return afterDrop;
    }
    public int[] dropRain2(int[] beforeDrop, int numOfRainDrop, int position){
        int[] afterDrop = beforeDrop.clone();
        showLandScape(beforeDrop, afterDrop);
        while(numOfRainDrop -- > 0){
            int positionToDropRain = position;
            for(int i = position - 1; i >= 0; i --){
                if(afterDrop[i] > afterDrop[positionToDropRain]){
                    break;
                }else if(afterDrop[i] < afterDrop[positionToDropRain]){
                    positionToDropRain = i;
                }
            }
            if(positionToDropRain != position){
                if(positionToDropRain != 0) {
                    afterDrop[positionToDropRain]++;
                }
                continue;
            }
            for(int i = position + 1; i < afterDrop.length; i ++){
                if(afterDrop[i] > afterDrop[positionToDropRain]){
                    break;
                }else if(afterDrop[i] < afterDrop[positionToDropRain]){
                    positionToDropRain = i;
                }
            }
            if(positionToDropRain != beforeDrop.length - 1){
                afterDrop[positionToDropRain] ++;
            }
        }
        showLandScape(beforeDrop, afterDrop);
        return afterDrop;
    }
    public static void main(String[] args) {
        PourWater s = new PourWater();
        int[] res = s.dropRain(new int[]{2,1,1,2,1,2,2}, 4, 3);
        for(int r : res){
            System.out.print(r + ", ");
        }
        System.out.println();
        res = s.dropRain(new int[]{2,1,1,2,1,2,2}, 8, 3);
        for(int r : res){
            System.out.print(r + ", ");
        }

        System.out.println();
        res = s.dropRain(new int[]{2,2,1,2,2,2,2}, 1, 2);
        for(int r : res){
            System.out.print(r + ", ");
        }

        System.out.println();
        res = s.dropRain(new int[]{2,2,1,2,2,2,2}, 1, 4);
        for(int r : res){
            System.out.print(r + ", ");
        }

        res = s.dropRain2(new int[]{2,1,1,2,1,2,2}, 4, 3);
        for(int r : res){
            System.out.print(r + ", ");
        }
        System.out.println();
        res = s.dropRain2(new int[]{2,1,1,2,1,2,2}, 8, 3);
        for(int r : res){
            System.out.print(r + ", ");
        }

        System.out.println();
        res = s.dropRain2(new int[]{2,2,1,2,2,2,2}, 1, 2);
        for(int r : res){
            System.out.print(r + ", ");
        }

        System.out.println();
        res = s.dropRain2(new int[]{2,2,1,2,2,2,2}, 1, 4);
        for(int r : res){
            System.out.print(r + ", ");
        }

    }
}
