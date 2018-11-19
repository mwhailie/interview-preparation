package edu.neu.companies.peloton;

import java.util.ArrayList;
import java.util.List;

public class WorkSchedule {
    public static List<String> findSchedule(int workHours, int dayHours, String pattern){
        List<String> schedule = new ArrayList<>();
        findSchedule(workHours, dayHours, pattern, schedule, new StringBuilder());
        return schedule;
    }

    private static void findSchedule(int workHours, int dayHours, String pattern, List<String> schedules, StringBuilder schedule) {
        if(schedule.length() == pattern.length()){
            if(workHours == 0){
                schedules.add(schedule.toString());
            }
            return;
        }
        if(workHours < 0){
            return;
        }
        if(pattern.charAt(schedule.length()) != '?'){
            int hour = pattern.charAt(schedule.length()) - '0';
            schedule.append(hour);
            findSchedule(workHours - hour, dayHours, pattern, schedules,schedule);
            schedule.deleteCharAt(schedule.length() - 1);
        }else{
            for(int i = 0;i <= dayHours && i <= workHours;i++){
                schedule.append(i);
                findSchedule(workHours - i, dayHours, pattern, schedules,schedule);
                schedule.deleteCharAt(schedule.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findSchedule(56,  8, "888????"));
        System.out.println(findSchedule(24,  4, "08??840"));
    }
}
