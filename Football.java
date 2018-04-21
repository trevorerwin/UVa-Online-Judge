import java.io.*;
import java.util.*;

/**
 * UVa 10194 - Football
 *
 * Objective: write a program that receives a tournament name, team names, and games played
 *            and output the current team standings
 *
 */
class Main {

    static class Team implements Comparable<Team> {
        String name;
        int gp; //games played
        int points;
        int wins;
        int ties;
        int losses;
        int goals;
        int against;
        int gd; //goal diff

        public Team(String name) {
            this.name = name;
        }

        public int compareTo(Team that) {
            if(this.points < that.points) return 1;
            if(this.points > that.points) return -1;

            if(this.wins < that.wins) return 1;
            if(this.wins > that.wins) return -1;

            if(this.gd < that.gd) return 1;
            if(this.gd > that.gd) return -1;

            if(this.goals < that.goals) return 1;
            if(this.goals > that.goals) return -1;

            if(this.gp < that.gp) return 1;
            if(this.gp > that.gp) return -1;

            return this.name.toLowerCase().compareTo(that.name.toLowerCase());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1")); //wtf
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Team> teamsMap = new HashMap<>();
        ArrayList<Team> teamsList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            teamsMap.clear();
            teamsList.clear();
            String tourney = br.readLine();
            int numTeams = Integer.parseInt(br.readLine());
            for(int j = 0; j < numTeams; j++){
                String line = br.readLine();
                Team team = new Team(line);
                teamsMap.put(line, team);
                teamsList.add(team);
            }

            int G = Integer.parseInt(br.readLine());
            for(int games = 0; games < G; games++){
                StringTokenizer token = new StringTokenizer(br.readLine());
                Team team1 = teamsMap.get(token.nextToken("#"));
                int team1score = Integer.parseInt(token.nextToken("@").replace("#", ""));
                int team2score = Integer.parseInt(token.nextToken("#").replace("@", ""));
                Team team2 = teamsMap.get(token.nextToken().replace("#", ""));

                team1.gp++; team1.goals += team1score; team1.against += team2score; team1.gd += team1score - team2score;

                team2.gp++; team2.goals += team2score; team2.against += team1score; team2.gd += team2score - team1score;

                if(team1score > team2score){
                    team1.wins++; team1.points += 3;
                    team2.losses++;
                } else if (team2score > team1score){
                    team2.wins++; team2.points += 3;
                    team1.losses++;
                } else {
                    team1.ties++; team1.points++;
                    team2.ties ++; team2.points ++;
                }
            }

            //print out standings
            System.out.println(tourney);
            Collections.sort(teamsList);
            for(int t = 1; t < teamsList.size() + 1; t++){
                Team team = teamsList.get(t-1);
                System.out.printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n", t, team.name, team.points, team.gp,
                        team.wins, team.ties, team.losses, team.gd, team.goals, team.against);
            }
            if(i + 1 < N) System.out.println(" ");
        }

    }
}