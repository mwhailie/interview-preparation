package edu.neu.companies.viagogo;

import java.util.*;

public class GettingTicket{
    static class Event implements Comparable<Event>{
        int eventId;
        int x;
        int y;
        int distance;
        PriorityQueue<Integer> pricesHeap;
        public Event(String eventLine){
            try{
                String[] inputs = eventLine.split(" ");
                this.eventId = Integer.parseInt(inputs[0]);
                this.x = Integer.parseInt(inputs[1]);
                this.y = Integer.parseInt(inputs[2]);
                pricesHeap = new PriorityQueue<>();
                for(int i = 3; i < inputs.length; i++){
                    pricesHeap.add(Integer.parseInt(inputs[i]));
                }
            }catch(Exception e){
                throw new IllegalArgumentException();
            }
        }
        @Override
        public int compareTo(Event e) {
            if(this.distance != e.distance){
                return this.distance - e.distance;
            }

            if(this.pricesHeap.peek() != e.pricesHeap.peek()){
                return this.pricesHeap.peek() - e.pricesHeap.peek();
            }
            return this.eventId - e.eventId;
        }

    }

    static class Buyer{
        int x;
        int y;
        public Buyer(String buyerLine){
            String[] inputs = buyerLine.split(" ");
            this.x = Integer.parseInt(inputs[0]);
            this.y = Integer.parseInt(inputs[1]);
        }
    }

    public static void getTicketsToFans(Buyer b, List<Event> events){
        if(events.isEmpty()){
            System.out.println("-1 0");
            return;
        }
        PriorityQueue<Event> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(Event e : events){
            if(e.pricesHeap.isEmpty()){
                events.remove(e);
                continue;
            }
            e.distance = calculateManhattanDistance(b.x, b.y, e.x, e.y);
            pq.add(e);
            if(pq.size() > 1){
                pq.poll();
            }
        }
        if(pq.isEmpty()){
            System.out.println("-1 0");
            return;
        }
        Event result = pq.poll();
        int price = result.pricesHeap.poll();

        System.out.println(result.eventId + " " + price);
    }

    public static void main(String args[] ) throws Exception {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
//        Scanner scan = new Scanner(System.in);
//
//        int sizeOfWorld = Integer.parseInt(scan.nextLine());
//        int numberOfEvents = Integer.parseInt(scan.nextLine());
        List<Event> events = new ArrayList<>();
        List<Buyer> buyers = new ArrayList<>();
//        while(numberOfEvents > 0){
//            String eventLine = scan.nextLine();
//            events.add(new Event(eventLine));
//            numberOfEvents--;
//        }
//
//        int numberOfBuyers = Integer.parseInt(scan.nextLine());
//        while(numberOfBuyers > 0){
//            String buyerLine = scan.nextLine();
//            buyers.add(new Buyer(buyerLine));
//            numberOfBuyers--;
//        }

        events.add(new Event("1 1 1 50 70"));
        events.add(new Event("2 1 4 60 80"));
        buyers.add(new Buyer("3 3"));
        buyers.add(new Buyer("3 2"));
        buyers.add(new Buyer("3 2"));
        // The solution to the first sample above would be to output the following to console:
        // (Obviously, your solution will need to figure out the output and not just hard code it)
        for(Buyer b : buyers){
            getTicketsToFans(b, events);
        }
    }

    // The following method get the manhatten distance betwen two points (x1,y1) and (x2,y2)
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2)    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
