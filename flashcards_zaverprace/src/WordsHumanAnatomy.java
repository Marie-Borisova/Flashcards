import java.io.*;
import java.util.*;

public class WordsHumanAnatomy {
    private final HashMap<String, String> wordshumananatomy = new HashMap<>();
    private int correctTranslation = 0;
    private final ArrayList<String> incorrectlyTranslatedWords = new ArrayList<>();
    Random r = new Random();
    Scanner sc = new Scanner(System.in);

    /**
     * Reads words from the given text file, divides them and then inserts them into a HashMap.
     */
    public void readWordsFromTextFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("wordshumananatomy.txt"))) {
            String wordanatomy;
            while ((wordanatomy = br.readLine()) != null) {
                String[] dividedWordsanatomy = wordanatomy.split(";");
                if (dividedWordsanatomy.length > 1) {
                    String englishWordanatomy = dividedWordsanatomy[0];
                    String czechWordanatomy = dividedWordsanatomy[1];
                    wordshumananatomy.put(englishWordanatomy, czechWordanatomy);
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
        HashSet<String> nonduplicateWordsBodyParts = new HashSet<>();
        System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
        String usersChoice = sc.next();
        String translation;
        while (!(usersChoice.equalsIgnoreCase("CZ") || usersChoice.equalsIgnoreCase("ENG"))) {
            System.out.println("Input the abbreviation CZ or ENG!");
            System.out.print("Choose the Czech (CZ) or the English (ENG) meaning: ");
            usersChoice = sc.next();
        }
        if (usersChoice.equalsIgnoreCase("CZ")) {
            ArrayList<String> czechWordsanatomy = new ArrayList<>(wordshumananatomy.values());
            int randomIndexValue;
            String randomValue;
            while (nonduplicateWordsBodyParts.size() != czechWordsanatomy.size()) {
                randomIndexValue = r.nextInt(czechWordsanatomy.size());
                randomValue = czechWordsanatomy.get(randomIndexValue);
                nonduplicateWordsBodyParts.add(randomValue);

            }

            for (String bodypartCzech : nonduplicateWordsBodyParts) {
                System.out.println(bodypartCzech);
                System.out.print("Translate to English: ");
                translation = sc.next();
                String czechToEnglish = "";
                for (Map.Entry<String, String> entry : wordshumananatomy.entrySet()) {
                    if (entry.getValue().equals(bodypartCzech)) {
                        czechToEnglish = entry.getKey();
                    }

                }
                if (translation.equalsIgnoreCase(czechToEnglish)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(bodypartCzech);
                    System.out.println("Incorrect!" + "\n" + "The correct translation is: " + czechToEnglish);
                    System.out.println();
                }
            }

        }
        if (usersChoice.equalsIgnoreCase("ENG")) {
            ArrayList<String> englishWords = new ArrayList<>(wordshumananatomy.keySet());
            while (nonduplicateWordsBodyParts.size() != englishWords.size()) {
                int randomIndexKey = r.nextInt(englishWords.size());
                String randomKey = englishWords.get(randomIndexKey);
                nonduplicateWordsBodyParts.add(randomKey);

            }

            for (String bodypartEnglish : nonduplicateWordsBodyParts) {
                System.out.println(bodypartEnglish);
                System.out.print("Translate to Czech (without diacritics): ");
                translation = sc.next();
                String englishToCzech = "";
                for (Map.Entry<String, String> entry : wordshumananatomy.entrySet()) {
                    if (entry.getKey().equals(bodypartEnglish)) {
                        englishToCzech = entry.getValue();
                    }

                }
                if (translation.equalsIgnoreCase(englishToCzech)) {
                    correctTranslation++;
                    System.out.println("Correct!");
                    System.out.println();
                } else {
                    incorrectlyTranslatedWords.add(bodypartEnglish);
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
        int percentage = (correctTranslation * 100) / wordshumananatomy.size();
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
