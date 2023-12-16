package hangman;

public class Word {
    public static String[] words = new String[]{"semut", "rusa", "beruang", "kanguru", "katak", "lebah", "komodo", "angsa", "paus", "gajah", "harimau", "badak", "alpaca", "capybara"};
    public Word(){};
    public static String getWord(){
        int i = (int)(Math.random()*(double)words.length);
        return words[i];
    }
}
