import java.io.*;
import java.util.*;

public class WordsFruitsAndVegetables {

    private final HashMap<String, String> wordsfruitsandvegetables = new HashMap<>();
    private int correctTranslation = 0;
    private final ArrayList<String> incorrectlyTranslatedWords = new ArrayList<>();
    Random r = new Random();
    Scanner sc = new Scanner(System.in);

    /**
     * Reads words from the given text file, divides them and then inserts them into a HashMap.
     */
    public void readWordsFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("wordsfruits&vegetables.txt"))) {
            String wordfruit;
            while ((wordfruit = br.readLine()) != null) {
                String[] dividedWordsfruits = wordfruit.split(";");
                if (dividedWordsfruits.length > 1) {
                    String englishWordfruit = dividedWordsfruits[0];
                    String czechWordfruit = dividedWordsfruits[1];
                    wordsfruitsandvegetables.put(englishWordfruit, czechWordfruit);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prompts the user to choose either the Czech or the English meaning of the words.
     * Then randomly generates the words in the chosen meaning for the user to translate to the other meaning.
     */

    public void czechOrEnglishMeaning() {
        HashSet<String> nonduplicateWordsFruitsAndVeggies = new HashSet<>();
        System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
        String usersChoice = sc.next();
        String translation;
        while (!(usersChoice.equalsIgnoreCase("CZ") || usersChoice.equalsIgnoreCase("ENG"))) {
            System.out.println("Input the abbreviation CZ or ENG!");
            System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
            usersChoice = sc.next();
        }
        if (usersChoice.equalsIgnoreCase("CZ")) {
            ArrayList<String> czechWordsanatomy = new ArrayList<>(wordsfruitsandvegetables.values());
            int randomIndexValue;
            String randomValue;
            while (nonduplicateWordsFruitsAndVeggies.size() != czechWordsanatomy.size()) {
                randomIndexValue = r.nextInt(czechWordsanatomy.size());
                randomValue = czechWordsanatomy.get(randomIndexValue);
                nonduplicateWordsFruitsAndVeggies.add(randomValue);

            }

            for (String fruitOrVegetableCzech : nonduplicateWordsFruitsAndVeggies) {
                System.out.println(fruitOrVegetableCzech);
                System.out.print("Translate to English: ");
                translation = sc.next();
                String czechToEnglish = "";
                for (Map.Entry<String, String> entry : wordsfruitsandvegetables.entrySet()) {
                    if (entry.getValue().equals(fruitOrVegetableCzech)) {
                        czechToEnglish = entry.getKey();
                    }

                }
                if (translation.equalsIgnoreCase(czechToEnglish)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(fruitOrVegetableCzech);
                    System.out.println("Incorrect!" + "\n" + "The correct translation is: " + czechToEnglish);
                    System.out.println();
                }
            }

        }
        if (usersChoice.equalsIgnoreCase("ENG")) {
            ArrayList<String> englishWords = new ArrayList<>(wordsfruitsandvegetables.keySet());
            while (nonduplicateWordsFruitsAndVeggies.size() != englishWords.size()) {
                int randomIndexKey = r.nextInt(englishWords.size());
                String randomKey = englishWords.get(randomIndexKey);
                nonduplicateWordsFruitsAndVeggies.add(randomKey);

            }

            for (String fruitOrVegetableEnglish : nonduplicateWordsFruitsAndVeggies) {
                System.out.println(fruitOrVegetableEnglish);
                System.out.print("Translate to Czech (without diacritics): ");
                translation = sc.next();
                String englishToCzech = "";
                for (Map.Entry<String, String> entry : wordsfruitsandvegetables.entrySet()) {
                    if (entry.getKey().equals(fruitOrVegetableEnglish)) {
                        englishToCzech = entry.getValue();
                    }

                }
                if (translation.equalsIgnoreCase(englishToCzech)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(fruitOrVegetableEnglish);
                    System.out.println("Incorrect!" + "\n" + "The correct translation is: " + englishToCzech);
                    System.out.println();
                }
            }
        }
    }

    /**
     * Calculates the percentage of the words the user translated correctly.
     *
     * @return the percentage score
     */
    public String percentageScore() {
        int percentage = (correctTranslation * 100) / wordsfruitsandvegetables.size();
        return "You translated " + percentage + "%" + " of the words correctly";

    }

    /**
     * Writes the words that the user didn't translate correctly into a new text file.
     */

    public void writeNotGuessedWords() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("incorrectlytranslatedwords.txt"))) {
            bw.write(incorrectlyTranslatedWords.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
