import java.io.*;
import java.util.*;

public class WordsAnimals {

    private final HashMap<String, String> wordsanimals = new HashMap<>();
    private int correctTranslation = 0;
    private final ArrayList<String> incorrectlyTranslatedWords = new ArrayList<>();
    Random r = new Random();
    Scanner sc = new Scanner(System.in);


    /**
     * Reads words from the given text file, divides them and then inserts them into a HashMap.
     */
    public void readWordsFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("wordsanimals.txt"))) {
            String wordanimal;
            while ((wordanimal = br.readLine()) != null) {
                String[] dividedWords = wordanimal.split(";");
                if (dividedWords.length > 1) {
                    String englishWordanimal = dividedWords[0];
                    String czechWordanimal = dividedWords[1];
                    wordsanimals.put(englishWordanimal, czechWordanimal);
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
        HashSet<String> nonduplicateWordsAnimals = new HashSet<>();
        System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
        String usersChoice = sc.next();
        String translation;
        while (!(usersChoice.equalsIgnoreCase("CZ") || usersChoice.equalsIgnoreCase("ENG"))) {
            System.out.println("Input the abbreviation CZ or ENG!");
            System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
            usersChoice = sc.next();
        }
        if (usersChoice.equalsIgnoreCase("CZ")) {
            ArrayList<String> czechWords = new ArrayList<>(wordsanimals.values());
            int randomIndexValue;
            String randomValue;
            while (nonduplicateWordsAnimals.size() != czechWords.size()) {
                randomIndexValue = r.nextInt(czechWords.size());
                randomValue = czechWords.get(randomIndexValue);
                nonduplicateWordsAnimals.add(randomValue);

            }

            for (String animalCzech : nonduplicateWordsAnimals) {
                System.out.println(animalCzech);
                System.out.print("Translate to English: ");
                translation = sc.next();
                String czechToEnglish = "";
                for (Map.Entry<String, String> entry : wordsanimals.entrySet()) {
                    if (entry.getValue().equals(animalCzech)) {
                        czechToEnglish = entry.getKey();
                    }

                }
                if (translation.equalsIgnoreCase(czechToEnglish)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(animalCzech);
                    System.out.println("Incorrect!" + "\n" + "The correct translation is: " + czechToEnglish);
                    System.out.println();
                }
            }

        }
        if (usersChoice.equalsIgnoreCase("ENG")) {
            ArrayList<String> englishWords = new ArrayList<>(wordsanimals.keySet());
            while (nonduplicateWordsAnimals.size() != englishWords.size()) {
                int randomIndexKey = r.nextInt(englishWords.size());
                String randomKey = englishWords.get(randomIndexKey);
                nonduplicateWordsAnimals.add(randomKey);

            }

            for (String animalEnglish : nonduplicateWordsAnimals) {
                System.out.println(animalEnglish);
                System.out.print("Translate to Czech (without diacritics): ");
                translation = sc.next();
                String englishToCzech = "";
                for (Map.Entry<String, String> entry : wordsanimals.entrySet()) {
                    if (entry.getKey().equals(animalEnglish)) {
                        englishToCzech = entry.getValue();
                    }

                }
                if (translation.equalsIgnoreCase(englishToCzech)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(animalEnglish);
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
        int percentage = (correctTranslation * 100) / wordsanimals.size();
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
