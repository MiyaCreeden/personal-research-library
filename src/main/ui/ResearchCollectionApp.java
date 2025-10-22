package ui;

import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.ResearchCollection;
import model.ResearchPaper;
import persistance.JsonReader;
import persistance.JsonWriter;

// Represents a Research Collection application (user interface)
// lets you view your reserach collection, add multiple papers,
// compute a rating for a paper, filter the collection based off a
// given author or displine

//Credit: code written in this class is inspired by FlashcardReviewer Lab project 
//(https://us.prairielearn.com/pl/workspace/3579775)
// and TellerApp Project (https://github.students.cs.ubc.ca/CPSC210/TellerApp.git)
// and JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport
public class ResearchCollectionApp {
    private ResearchCollection collection;
    private Scanner scanner;
    private boolean isRunning;
    private int currentIndex;
    private static final String JSON_STORE = "./data/researchcollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates a new instance of the application console
    public ResearchCollectionApp() throws FileNotFoundException {
        intialize();

        System.out.println("Welcome to your Research Collection!");

        while (this.isRunning) {
            runMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: intializes the program with intial values
    public void intialize() {
        this.collection = new ResearchCollection();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.currentIndex = 0;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // EFFECTS: constructs the display menu for the application
    public void displayMainMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new paper to your collection");
        System.out.println("v: View your collection");
        System.out.println("f: Search collection by author, title, or displine...");
        System.out.println("r: View papers you have read");
        System.out.println("u: View papers you have not read yet");
        System.out.println("s: Save your collection to file");
        System.out.println("l: Load your collection from file");
        System.out.println("q: Exit the application");
    }

    // MODIFIES: this
    // EFFECTS: displays and processes the user input for main menu
    public void runMainMenu() {
        displayMainMenu();
        String input = this.scanner.nextLine();
        processMainMenuCommands(input);
    }

    // EFFECTS: processes users input from main menu
    public void processMainMenuCommands(String input) {
        switch (input) {
            case "a": addNewPaper();
                break;
            case "v": viewCollection();
                break;
            case "f": viewByFilter();
                break;
            case "r": viewRead();
                break;
            case "u": viewUnread();
                break;
            case "s": save();
                break;
            case "l": load();
                break;
            case "q": quit();
                break;
            default:
                System.out.println("Invalid option inputted, try again");
        }
    }

    // EFFECTS: reloads research collection from file
    private void load() {
        try {
            collection = jsonReader.read();
            System.out.println("Loaded " + "My papers" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves research collection to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(collection);
            jsonWriter.close();
            System.out.println("Saved " + "My papers" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: reserach collection
    // EFFECTS: adds a new paper to your research collection
    public void addNewPaper() {
        System.out.println("Please enter the title of the paper");
        String title = this.scanner.nextLine();

        System.out.println("\nPlease enter the paper's author");
        String author = this.scanner.nextLine();

        System.out.println("\nPlease enter displine");
        String displine = this.scanner.nextLine();

        ResearchPaper paper = new ResearchPaper(title, author, displine);

        this.collection.addPaper(paper);
        System.out.println("\nNew paper added to collection!");
    }

    // MODIFIES: this
    // EFFECTS: displays entire Reserach Collection
    public void viewCollection() {

        for (ResearchPaper paper : collection.getResearchCollection()) {
            System.out.println(paper.getPTitle());
        }

        displayGivenCollection(this.collection);

    }

    // EFFECTS: displays current collection and shows view menu
    public void displayGivenCollection(ResearchCollection collection) {
        if (0 == collection.getResearchCollection().size()) {
            System.out.println("Error: Your Research Collection is empty, please add a paper.");
            return;
        }

        displayViewMenu();
        String input = ""; /// this line of code is cause an invalid input message after evalutating a
                           /// rating for a paper
        while (!input.equals("q")) {
            ResearchPaper currentPaper = collection.getResearchCollection().get(this.currentIndex);
            displayPaper(currentPaper);
            input = this.scanner.nextLine();
            processVcommands(input, collection);
        }
        this.currentIndex = 0;
    }

    // EFFECTS: displays given flashcard
    public void displayPaper(ResearchPaper paper) {
        System.out.println(paper.getPTitle());
        System.out.println("By:");
        System.out.println(paper.getAuthor());
        System.out.println("Displine:");
        System.out.println(paper.getDisipline());
    }

    // MODIFIES: this
    // EFFECTS: displays list of papers marked as read
    public void viewRead() {
        ArrayList<ResearchPaper> read = new ArrayList<>();

        for (ResearchPaper currentPaper : this.collection.getResearchCollection()) {

            if (currentPaper.getReadStatus() == true) {
                read.add(currentPaper);
            }
        }

        for (ResearchPaper paper : read) {
            System.out.println(paper.getPTitle());
        }
    }

    // MODIFIES: this
    // EFFECTS: displays list of papers marked as read
    public void viewUnread() {
        ArrayList<ResearchPaper> unread = new ArrayList<>();

        for (ResearchPaper currentPaper : this.collection.getResearchCollection()) {

            if (currentPaper.getReadStatus() == false) {
                unread.add(currentPaper);
            }
        }

        for (ResearchPaper paper : unread) {
            System.out.println(paper.getPTitle());
        }

    }

    // EFFECTS: displays a list of commands that can be used in the
    // view menu
    public void displayViewMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("o: Rate this paper");
        System.out.println("r: Mark this paper as read");
        System.out.println("u: Mark this paper as unread");
        System.out.println("n: Next Paper");
        System.out.println("p: Previous Paper");
        System.out.println("q: Return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: displays and processses the user input for the view menu
    public void runViewMenu() {
        displayViewMenu();
        String input = this.scanner.nextLine();
        processVcommands(input, collection);
    }

    // EFFECTS: processes the users input in the view menu
    public void processVcommands(String input, ResearchCollection collection) {
        System.out.print("\n");
        ResearchPaper currentPaper = collection.getResearchCollection().get(this.currentIndex);
        switch (input) {
            case "o":
                ratePaper(currentPaper);
                break;
            case "r":
                markPaperAsRead(currentPaper);
                break;
            case "u":
                markPaperAsUnread(currentPaper);
                break;
            case "n":
                getNextPaper(collection);
                break;
            case "p":
                getPreviousPaper();
                break;
            case "q": System.out.println("Returning to main menu:");
                break;
            default: System.out.println("Invalid option inputted, try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: if there is another paper, increment index
    public void getNextPaper(ResearchCollection collection) {
        if (this.currentIndex >= collection.getResearchCollection().size() - 1) {
            System.out.println("Error: Thats the end of your collection");
        } else {
            this.currentIndex++;
        }

        displayViewMenu();

    }

    // MODIFIES: this
    // EFFECTS: if there is a previous paper, decrements index
    public void getPreviousPaper() {
        if (this.currentIndex <= 0) {
            System.out.println("Error: Thats the start of your collection");
        } else {
            this.currentIndex--;
        }

        displayViewMenu();
        addNewPaper();
    }

    // MODIFIES: this
    // EFFECTS: processes a user filter input and displays a list of
    // papers matching the filter
    public void viewByFilter() {

        System.out.println("...please enter your search term");
        String filter = this.scanner.nextLine();

        if (collection.filterCollection(filter).isEmpty()) {
            System.out.println("Sorry, no papers match that search term");
        }

        for (ResearchPaper paper : collection.filterCollection(filter)) {
            System.out.println(paper.getPTitle());
        }

    }

    // MODIFIES: research paper
    // EFFECTS: marks a paper as read
    public void markPaperAsRead(ResearchPaper paper) {
        System.out.println(paper.getPTitle() + " was read!");
        paper.markAsRead();

        displayViewMenu();
    }

    // MODIFIES: research paper
    // EFFECTS: marks a paper as unread
    public void markPaperAsUnread(ResearchPaper paper) {
        System.out.println(paper.getPTitle() + " hasn't been read.");
        paper.markAsUnread();

        displayViewMenu();
    }

    // REQUIRES: 5 ≥ (enjoy and usefull) ≥ 0
    // MODIFIES: reserach paper
    // EFFECTS: computes rating for paper
    public void ratePaper(ResearchPaper paper) {

        try {
            System.out.println("Please enter your enjoyment of this paper from 0 to 5:");
            int enjoy = Integer.parseInt(this.scanner.nextLine());

            System.out.println("Please enter how usefull this paper was from 0 to 5:");
            int usefull = Integer.parseInt(this.scanner.nextLine());

            if (enjoy >= 0 && enjoy <= 5 && usefull >= 0 && usefull <= 5) {
                paper.overallRating(enjoy, usefull);
                System.out.println("You rated this paper:" + paper.getOverallRating());
            } else {

                System.out.println("Invalid inputs: values should be 0 =< value =< 5, try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input numbers for enjoy and usefull");
        }

        displayViewMenu();

    }

    // MODIFIES: this
    // EFFECTS: quits application, displays closing message
    public void quit() {
        System.out.println("Thanks for doing your Research!");
        System.out.println("Have a good day!");
        this.isRunning = false;
    }

}
