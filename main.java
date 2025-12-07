import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class main {
    static Random rand = new Random();
    static int money = 0;
    static int factory_capacity = 1;
    static int factory_capacity_left = 0;
    static String name_id[] = {"wood","iron","silver","gold","platinum"};
    static int quantity[] = {0,0,0,0,0};
    static int production_cost[] = {1000,4000,20000,50000,250000};
    static int sell_price[] = {1100,5000,25000,60000,300000};
    static int factory_output[] = {1,0,0,0,0};
    static int sell_amount[] = {1,0,0,0,0};
    static float research_chance[] = {1f,0.01f,0.002f,0.0005f,0.00001f};
    static boolean researched[] = {true,false,false,false,false};
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            check_input(input);
            create_ressources();
            sell_ressources();
            change_prices();
            research_ressource();
            visuals();
        }
    }

    public static void create_ressources() {
        for (int i = 0; i < factory_output.length; i++) {
            for (int j = 0; j < factory_output[i]; j++) {
                quantity[i] += 1;
                money -= production_cost[i];
            }
        }
    }
    
    public static void sell_ressources() {
        for (int i = 0; i < sell_amount.length; i++) {
            for (int j = 0; j < sell_amount[i]; j++) {
                if (quantity[i] -1 >= 0) {
                    quantity[i] -= 1;
                    money += sell_price[i];
                }
            }
        }
    }

    public static void research_ressource() {
        for (int i = 0; i < researched.length; i++) {
            if (researched[i] == false) {
                float chance = rand.nextFloat();
                if (chance <= research_chance[i]) {
                    researched[i] = true;
                }
            }
        }
    }

    public static void check_input(String input) {
        String[] splitted = input.split(" ");

        if (splitted[0].equals("production")) {
            if (factory_capacity_left - Integer.parseInt(splitted[2]) >= 0) {
                if (researched[Arrays.asList(name_id).indexOf(splitted[1])] != true) {
                    return;
                }
                factory_capacity_left -= Integer.parseInt(splitted[2]);
                List<String> names = Arrays.asList(name_id);
                int nameindex = names.indexOf(splitted[1]);
                factory_output[nameindex] += Integer.parseInt(splitted[2]);
            }
        }
        if (splitted[0].equals("sell_amount")) {
            List<String> names = Arrays.asList(name_id);
            int nameindex = names.indexOf(splitted[1]);
            sell_amount[nameindex] = Integer.parseInt(splitted[2]);
        }
        if (splitted[0].equals("factory_upgrade")) {
            int factory_upgrade_cost = factory_capacity * 10000;
            if (money - factory_upgrade_cost >= 0) {
                money -= factory_upgrade_cost;
                factory_capacity += 1;
                factory_capacity_left += 1;
            }
        }
    }

    public static void change_prices() {
        //later
    }

    public static void visuals() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("Money: " + money);
        System.out.println("  |Industry capacity: " + (factory_capacity - factory_capacity_left) + "/" + factory_capacity);
        System.out.print("|");
        for (int i=0; i<name_id.length; i++) {
            System.out.print(name_id[i]);
            System.out.print("|");
        }
        System.out.println(" >>>Ressources");
        System.out.print("|");
        for (int i=0; i<name_id.length; i++) {
            System.out.print(quantity[i]);
            for (int j=0; j<name_id[i].length()-Integer.toString(quantity[i]).length(); j++ ){
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println(" >>>Quantities");
        System.out.print("|");
        for (int i=0; i<name_id.length; i++) {
            System.out.print(factory_output[i]);
            for (int j=0; j<name_id[i].length()-Integer.toString(factory_output[i]).length(); j++ ){
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println(" >>>Ressourcegain per cycle");
        System.out.print("|");
        for (int i=0; i<name_id.length; i++) {
            System.out.print(sell_amount[i]);
            for (int j=0; j<name_id[i].length()-Integer.toString(sell_amount[i]).length(); j++ ){
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println(" >>>Sell amount per cycle");
        System.out.print("|");
        for (int i=0; i<name_id.length; i++) {
            if (researched[i] == true) {
                System.out.print("✓");
            } else {
                System.out.print("X");
            }
            for (int j=0; j<name_id[i].length()-Integer.toString(sell_amount[i]).length(); j++ ){
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println(" >>>Researched");
    }
}