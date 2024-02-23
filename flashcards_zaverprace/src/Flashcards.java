import java.util.*;

public class Flashcards {
    Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user to choose from the given categories of words.
     * Then creates an object of the chosen category and calls all of its methods.
     */
    public void chooseACategoryOfWords() {
        int usersCategory = 0;
        System.out.println(writeOutTheCategories());
        boolean isANumber = false;
        do {
            try {
                usersCategory = sc.nextInt();
                while (usersCategory > 3 || usersCategory <= 0) {
                    System.out.println("Input 1, 2 or 3!");
                    System.out.println(writeOutTheCategories());
                    usersCategory = sc.nextInt();
                }
                isANumber = true;
            } catch (InputMismatchException e) {
                System.out.println("Input 1, 2 or 3!");
                System.out.println(writeOutTheCategories());
                sc.next();
            }
        } while (!isANumber);

        if (usersCategory == 1) {
            System.out.println("You chose the category 'animals'");
            WordsAnimals animals = new WordsAnimals();
            animals.readWordsFromTextFile();
            animals.czechOrEnglishMeaning();
            animals.writeNotGuessedWords();
            System.out.println(animals.percentageScore());
        }
        if (usersCategory == 2) {
            System.out.println("You chose the category 'human anatomy'");
            WordsHumanAnatomy anatomy = new WordsHumanAnatomy();
            anatomy.readWordsFromTextFile();
            anatomy.czechOrEnglishMeaning();
            anatomy.writeNotGuessedWords();
            System.out.println(anatomy.percentageScore());
        }
        if (usersCategory == 3) {
            System.out.println("You chose the category 'fruits and vegetables'");
            WordsFruitsAndVegetables fruitsAndVegetables = new WordsFruitsAndVegetables();
            fruitsAndVegetables.readWordsFromTextFile();
            fruitsAndVegetables.czechOrEnglishMeaning();
            fruitsAndVegetables.writeNotGuessedWords();
            System.out.println(fruitsAndVegetables.percentageScore());
        }

    }


    /**
     * Writes out the categories of words for the user to choose from.
     *
     * @return the categories of words
     */
    public String writeOutTheCategories() {
        return """
                Choose a category of words:\s
                1. animals
                2. human anatomy
                3. fruits and vegetables""";

    }


}
