import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;


class Main {

    private static ArrayList<String> listOfTasks = new ArrayList<String>();
    static boolean keep_going = true;
    static int taskCount;
    public static void main(String[] args) {
    

        clearScreen();
        loadFromFiles();

        while(keep_going == true) {
            
            printTasks();
            menuOption();
        
        }

        saveToFiles();
    
    }


    public static void addTask(String task) {
        listOfTasks.add(task);
        taskCount++;
        clearScreen();
    }

    public static void removeTask(int index) {
        System.out.println("");
        printTasks();
        try {
            listOfTasks.remove(index-1);
            taskCount--;
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
        clearScreen();

        
    }

    public static void printTasks() {
      
        
        System.out.println("_____________________________");
        System.out.println("Current Tasks: ");

        if (listOfTasks.size() == 0) {
            System.out.println("No tasks to display");
            System.out.println("_____________________________");
            return;
        }

        int i = 1;
        for (String task : listOfTasks) {
            System.out.println("\tTask #"+ i + ": " + task);
            i++;
        }
        System.out.println("_____________________________");
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static void printMenu() {
        System.out.println("1. Add task");
        System.out.println("2. Remove task");
        System.out.println("3. Save and Exit");
    }

    public static String getInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your input: ");
        try {
            String userInput = input.nextLine();
            return userInput;
        } catch (Exception e) {
            System.out.println("Invalid input");
            return null;
        }
    }
    
    /** Description: This method will print the menu and get the user input*/
    public static void menuOption() {
        printMenu();
        String option = getInput();    

        switch (option) {
            case "1":
                clearScreen();
                printTasks();
                System.out.println("Enter task to add");
                String taskToAdd = getInput();
                addTask(taskToAdd);
                saveToFiles();
                break;
            case "2":
            
                clearScreen();
                printTasks();
                System.out.println("Enter # of task to remove");
                int taskToRemove;
                try{
                    taskToRemove = Integer.parseInt(getInput());
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    return;
                }
                removeTask(taskToRemove);
                saveToFiles();
                break;
            case "3":
                endProgram();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }



    }
    
    public static boolean saveToFiles() {
        
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
            
            for (String task : listOfTasks) {
                writer.write(task + "\n");
                
            }
            
            writer.close();
        
        } catch (Exception e) {
            // System.out.println("Error saving to file");
            try {
                BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
                reader.close();
            } catch (Exception e2) {
                System.out.println("Error saving to file");
                System.out.println(e2.getStackTrace());
            }
            return false;
        }
        
        return true;
    }
        
    

    public static boolean loadFromFiles() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;
            
            while((line = reader.readLine()) != null) {
                listOfTasks.add("" + line);  
                taskCount++;   
            }
            
            reader.close();

        } catch(Exception e) {
            // System.out.println();
            // System.out.println("Error loading from file:");
            // System.out.println(e.getStackTrace());
            // System.out.println();
            return false;
        }
        
        return true;
    }

    public static void endProgram() {
        
        clearScreen();
        printTasks();
        System.out.println("See you next time!");
        System.out.println();
        keep_going = false;
        
    }

    



}