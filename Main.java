import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};

    
    static int[][][] profit = new int[MONTHS][DAYS][COMMS];

    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for (int m = 0; m < MONTHS; m++) {
        String fileName = "Data_Files/" + months[m] + ".txt";

        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.nextLine(); 

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");

                int day = Integer.parseInt(parts[0]) - 1;
                String comm = parts[1];
                int value = Integer.parseInt(parts[2]);

                int index = -1;
                for (int i = 0; i < COMMS; i++) {
                    if (commodities[i].equals(comm)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    profit[m][day][index] = value;
                }
            }

            sc.close();

        } catch (Exception e) {
            
        }
    }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        int maxProfit = Integer.MIN_VALUE;
    String best = "";

    for (int c = 0; c < COMMS; c++) {
        int total = 0;

        for (int day = 0; day < DAYS; day++) {
            total += profit[month][day][c];
        }

        if (total > maxProfit) {
            maxProfit = total;
            best = commodities[c];
        }
    }

    return best; 
    }

    public static int totalProfitOnDay(int month, int day) {
        int sum = 0;

    for (int c = 0; c < COMMS; c++) {
        sum += profit[month][day][c];
    }

    return sum;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
         int index = -1;

    for (int i = 0; i < COMMS; i++) {
        if (commodities[i].equals(commodity)) {
            index = i;
            break;
        }
    }

    int sum = 0;

    for (int m = 0; m < MONTHS; m++) {
        for (int d = from; d <= to; d++) {
            sum += profit[m][d][index];
        }
    }

    return sum;
    }

    public static int bestDayOfMonth(int month) { 
         int bestDay = 0;
    int bestValue = Integer.MIN_VALUE;

    for (int d = 0; d < DAYS; d++) {

        int sum = 0;

        for (int c = 0; c < COMMS; c++) {
            sum += profit[month][d][c];
        }

        if (sum > bestValue) {
            bestValue = sum;
            bestDay = d;
        }
    }

    return bestDay + 1;
    }
    
    public static String bestMonthForCommodity(String comm) { 
         int index = -1;

    for (int i = 0; i < COMMS; i++) {
        if (commodities[i].equals(comm)) {
            index = i;
            break;
        }
    }

    int bestMonth = 0;
    int bestValue = Integer.MIN_VALUE;

    for (int m = 0; m < MONTHS; m++) {

        int sum = 0;

        for (int d = 0; d < DAYS; d++) {
            sum += profit[m][d][index];
        }

        if (sum > bestValue) {
            bestValue = sum;
            bestMonth = m;
        }
    }

    return months[bestMonth];
    }

    public static int consecutiveLossDays(String comm) { 
         int index = -1;

    for (int i = 0; i < COMMS; i++) {
        if (commodities[i].equals(comm)) {
            index = i;
            break;
        }
    }

    int maxStreak = 0;
    int current = 0;

    for (int m = 0; m < MONTHS; m++) {
        for (int d = 0; d < DAYS; d++) {

            if (profit[m][d][index] < 0) {
                current++;
                if (current > maxStreak) {
                    maxStreak = current;
                }
            } else {
                current = 0;
            }
        }
    }

    return maxStreak;
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
         int index = -1;

    for (int i = 0; i < COMMS; i++) {
        if (commodities[i].equals(comm)) {
            index = i;
            break;
        }
    }

    int count = 0;

    for (int m = 0; m < MONTHS; m++) {
        for (int d = 0; d < DAYS; d++) {
            if (profit[m][d][index] > threshold) {
                count++;
            }
        }
    }

    return count;
    }

    public static int biggestDailySwing(int month) { 
         int bestDay = 1;
    int bestSwing = 0;

    int prev = 0;
    for (int c = 0; c < COMMS; c++) {
        prev += profit[month][0][c];
    }

    for (int d = 1; d < DAYS; d++) {

        int current = 0;
        for (int c = 0; c < COMMS; c++) {
            current += profit[month][d][c];
        }

        int swing = Math.abs(current - prev);

        if (swing > bestSwing) {
            bestSwing = swing;
            bestDay = d;
        }

        prev = current;
    }

    return bestDay + 1;
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
         int i1 = -1;
    int i2 = -1;

    for (int i = 0; i < COMMS; i++) {
        if (commodities[i].equals(c1)) i1 = i;
        if (commodities[i].equals(c2)) i2 = i;
    }

    int sum1 = 0;
    int sum2 = 0;

    for (int m = 0; m < MONTHS; m++) {
        for (int d = 0; d < DAYS; d++) {
            sum1 += profit[m][d][i1];
            sum2 += profit[m][d][i2];
        }
    }

    if (sum1 >= sum2) {
        return c1 + " is better by " + (sum1 - sum2);
    } else {
        return c2 + " is better by " + (sum2 - sum1);
    } 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
