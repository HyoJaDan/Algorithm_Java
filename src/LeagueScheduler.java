import java.util.ArrayList;
import java.util.List;

public class LeagueScheduler {
    public static void main(String[] args) {
        int n = 6; // 팀의 수 (예: 6)
        if (n % 2 != 0) {
            n++; // 홀수 팀이면 더미 팀 추가
        }

        List<String> teams = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            teams.add("Team " + i);
        }

        List<List<String>> schedule = generateSchedule(teams);

        for (int day = 0; day < schedule.size(); day++) {
            System.out.println("Day " + (day + 1) + ":");
            for (String match : schedule.get(day)) {
                System.out.println(match);
            }
            System.out.println();
        }
    }

    private static List<List<String>> generateSchedule(List<String> teams) {
        List<List<String>> schedule = new ArrayList<>();
        int n = teams.size();
        int rounds = n - 1;
        int matchesPerDay = n / 2;

        for (int round = 0; round < rounds; round++) {
            List<String> daySchedule = new ArrayList<>();

            for (int match = 0; match < matchesPerDay; match++) {
                int home = (round + match) % (n - 1);
                int away = (n - 1 - match + round) % (n - 1);

                // 마지막 팀은 고정, 다른 팀은 순환
                if (match == 0) {
                    away = n - 1;
                }

                daySchedule.add(teams.get(home) + " vs " + teams.get(away));
            }
            schedule.add(daySchedule);
        }

        return schedule;
    }
}
