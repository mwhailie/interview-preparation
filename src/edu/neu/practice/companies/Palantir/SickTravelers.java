package edu.neu.practice.companies.Palantir;

import java.lang.reflect.Array;
import java.util.*;

public class SickTravelers {

    static String traceDisease(String[] initialStates) {
        // String[] res = new String[366];
        int numOfPeople = initialStates.length;
        List<String> stateOfPeopleToday = new ArrayList<>();
        List<String> stateOfPeopleTomorrow = new ArrayList<>();
        List<List<String>> peopleCities =  new ArrayList<>();
        Set<String> contagiousCityToday = new HashSet<>();
        Set<String> contagiousCityTomorrow = new HashSet<>();
        StringBuilder res = new StringBuilder();
        for(int people = 0; people < numOfPeople; people++){
            String[] inputs = initialStates[people].split(" ");
            String state = inputs[1];
            stateOfPeopleToday.add(state);
            if(!state.equalsIgnoreCase("HEALTHY")){
                contagiousCityToday.add(inputs[2]);
            }
            List<String> listOfCity = Arrays.asList(inputs).subList(2, inputs.length);
            peopleCities.add(listOfCity);
            res.append(inputs[0]).append(" ");

        }
        res.append("\n");
        int day = 1;
        for( ;day <= 365; day ++){

            res.append(String.join(" ", stateOfPeopleToday));
            res.append("\n");


            stateOfPeopleTomorrow = new ArrayList<>();
            contagiousCityTomorrow = new HashSet<>();
//            System.out.println(day + " " +contagiousCityToday);
            for(int people = 0; people < numOfPeople; people++){
                String state = stateOfPeopleToday.get(people);
                if("RECOVERING".equalsIgnoreCase(state) ){
                    stateOfPeopleTomorrow.add("HEALTHY");
                }else if("SICK".equalsIgnoreCase(state)) {
                    stateOfPeopleTomorrow.add("RECOVERING");
                    contagiousCityTomorrow.add(where(peopleCities, people, day ));
                }else{
                    if(contagiousCityToday.contains(where(peopleCities, people, day -1 ))){
                        stateOfPeopleTomorrow.add("SICK");
                        contagiousCityTomorrow.add(where(peopleCities, people, day ));
                    }else{
                        stateOfPeopleTomorrow.add("HEALTHY");
                    }
                }
            }
//            System.out.println(day + " contagiousCityToday " +contagiousCityToday + " contagiousCityTomorrow " + contagiousCityTomorrow);
            boolean allHeathy = true;
            for(String state : stateOfPeopleToday){
                if(!state.equalsIgnoreCase("HEALTHY")){
                    allHeathy = false;
                    break;
                }
            }
// System.out.println(Arrays.asList(stateOfPeopleToday).contains("RECOVERING"));
            if(allHeathy){
                break;
            }
            stateOfPeopleToday = stateOfPeopleTomorrow;
//             System.out.println(contagiousCityToday);
            contagiousCityToday = contagiousCityTomorrow;
        }
        res.append(day == 366 ? day - 1 : day);
        return res.toString();

    }
    private static String where(List<List<String>> peopleCities, int people, int day) {
        List<String> cities = peopleCities.get(people);
        return cities.get(day % cities.size());
    }

    public static void main(String[] args) {
//        String[] initialStates = new String[]{"Wison SICK PaloAlto DC London PaloAlto",
//                "Yun HEALTHY PaloAlto",
//                "Ali RECOVERING DC DC DC London",
//                "Jasmine HEALTHY London"};
//        System.out.println(traceDisease(initialStates));
//        initialStates = new String[]{"Wison HEALTHY PaloAlto DC London PaloAlto",
//                "Yun HEALTHY PaloAlto",
//                "Ali HEALTHY DC DC DC London",
//                "Jasmine HEALTHY London"};
//        System.out.println(traceDisease(initialStates));
//        String[]  initialStates3 = new String[]{"Wison HEALTHY PaloAlto",
//                "Yun SICK PaloAlto"};
//        System.out.println(traceDisease(initialStates3));
//        String[]  initialStates4 = new String[]{"Wison RECOVERING PaloAlto",
//                "Yun SICK PaloAlto"};
//        System.out.println(traceDisease(initialStates4));
        String[]  initialStates5 = new String[]{"Alice HEALTHY DC" ,"Bob SICK PaloAlto PaloAlto DC"};
        System.out.println(traceDisease(initialStates5));
        String[]  initialStates6 = new String[]{"Jasmine SICK DC DC DC DC DC DC", "Nick HEALTHY DC NY NY NY NY PA","Yun HEALTHY PA DC PA PA PA PA"};
        System.out.println(traceDisease(initialStates6));
        String[]  initialStates7 = new String[]{"Yun SICK PaloAlto DC London PaloAlto" , "Wilson HEALTHY PaloAlto" , "Ali RECOVERING DC DC DC London" , "Lee HEALTHY London"};
        System.out.println(traceDisease(initialStates7));
        String[]  initialStates8 = new String[]{"Ali SICK PA DC NY" ,
                "Isabella HEALTHY DC NY PA" ,
                "Lee HEALTHY NY PA DC" ,
                "Yun SICK PA" ,
                "Michael HEALTHY DC" ,
                "Jamal HEALTHY NY" ,
                "A RECOVERING DC NY" ,
                "B RECOVERING PA DC" ,
                "C HEALTHY NY PA"};
        System.out.println(traceDisease(initialStates8));
    }
}
