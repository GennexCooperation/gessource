import java.util.Random;
import java.util.Scanner;
public class main {
    static Random rand = new Random();
    static int money = 0;
    static int factory_capacity = 1;
    static int factory_capacity_left = 0;
    static int wood[] = {0,1000,1100};
    static int iron[] = {0,4000,5000};
    static int factory_output[] = {1,0};
    static int sell_amount[] = {1,0};
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            check_input(input);
            create_ressources();
            sell_ressources();
            change_prices();
            System.out.println("Money: " + money + " Wood: " + wood[0]);
        }
    }

    public static void create_ressources() {
        for (int i = 0; i < factory_output[0]; i++) {
            wood[0] += 1;
            money -= wood[1];
        }
    }

    public static void sell_ressources() {
        for (int i = 0; i < sell_amount[0]; i++) {
            if (wood[0] -1 >= 0) {
                wood[0] -= 1;
                money += wood[2];
            }
        }
    }

    public static void check_input(String input) {
        String[] splitted = input.split(" ");
        if (input.equals("production")) {
            System.out.println("Wood production: " + factory_output[0]);
            System.out.println("Wood sell amount: " + sell_amount[0]);
        }
        if (input.equals("capacity")) {
            System.out.println("Factory capacity: " + factory_capacity);
            System.out.println("Factory capacity left: " + factory_capacity_left);
        }
        if (splitted[0].equals("wood_production")) {
            if (factory_capacity_left - Integer.parseInt(splitted[1]) >= 0) {
                factory_capacity_left -= Integer.parseInt(splitted[1]);
                factory_output[0] += Integer.parseInt(splitted[1]);
            }
        }
        if (splitted[0].equals("wood_sell_amount")) {
            sell_amount[0] = Integer.parseInt(splitted[1]);
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
}