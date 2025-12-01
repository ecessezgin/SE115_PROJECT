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
        return 1234;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    public static int bestDayOfMonth(int month) { 
        return 1234; 
    }
    
    public static String bestMonthForCommodity(String comm) { 
        return "DUMMY"; 
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
