package classes;

import java.util.ArrayList;

public class Encrypt {
    private ArrayList<Character> charArray;
    private ArrayList<Character> cipherArray;
    private char cipher;
    private char cifa;
    private String message;

    public Encrypt() {
        charArray = new ArrayList();
        cipherArray = new ArrayList();
    }

    public String encrypt(String message) {
        int i;
        if (this.charArray.isEmpty()) {
            for(i = 0; i < message.length(); ++i) {
                this.charArray.add(message.charAt(i));
            }
        } else {
            this.charArray.clear();

            for(i = 0; i < message.length(); ++i) {
                this.charArray.add(message.charAt(i));
            }
        }

        this.cipherArray.clear();

        int val;
        for(i = 0; i < this.charArray.size(); ++i) {
            if (Character.isLetter((Character)this.charArray.get(i))) {
                if (Character.isLowerCase((Character)this.charArray.get(i))) {
                    this.cifa = this.encryptLowerCase((Character)this.charArray.get(i));
                } else if (Character.isUpperCase((Character)this.charArray.get(i))) {
                    this.cifa = this.encryptUpperCase((Character)this.charArray.get(i));
                }
            } else if (Character.isDigit((Character)this.charArray.get(i))) {
                val = Integer.parseInt(((Character)this.charArray.get(i)).toString());
                this.cifa = this.encryptNumber(val);
            } else if (Character.isWhitespace((Character)this.charArray.get(i))) {
                this.cifa = '^';
            } else {
                this.cifa = this.encryptPunctuationMark((Character)this.charArray.get(i));
            }

            this.cipherArray.add(this.cifa);
        }

        StringBuffer sb = new StringBuffer();

        for(val = 0; val < this.cipherArray.size(); ++val) {
            sb.append(this.cipherArray.get(val));
        }

        this.setMessage("encrypt");
        return sb.toString();
    }

    public char encryptNumber(int number) {
        int[] numbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        char[] numberSymbols = new char[]{'℅', '℉', '∂', '∏', '¶', 'æ', 'ƕ', 'ʤ', 'ϣ', 'ў'};

        for(int i = 0; i < numbers.length; ++i) {
            if (number == numbers[i]) {
                int formula = (i - 2) % 9;
                if (formula < 0) {
                    formula += 9;
                    this.cipher = numberSymbols[formula];
                } else {
                    this.cipher = numberSymbols[formula];
                }
            }
        }

        return this.cipher;
    }

    public char encryptUpperCase(char upperCaseLetter) {
        char[] upperCaseLetters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] upperCaseSymbols = new char[]{'Ѿ', '֍', 'ऴ', 'இ', 'ጹ', 'ᕔ', 'ᖱ', 'ᦀ', 'ᦄ', 'ᦆ', '℀', 'ᦇ', 'ᦊ', '᱓', 'Ͱ', 'ʓ', 'Ҵ', 'ʎ', '⃟', '⅝', '⇗', '∰', '⏏', '☩', '☯', 'ɮ'};

        for(int i = 0; i < upperCaseLetters.length; ++i) {
            if (upperCaseLetter == upperCaseLetters[i]) {
                int formula = (i - 2) % 25;
                if (formula < 0) {
                    formula += 25;
                    this.cipher = upperCaseSymbols[formula];
                } else {
                    this.cipher = upperCaseSymbols[formula];
                }
            }
        }

        return this.cipher;
    }

    public char encryptLowerCase(char lowerCaseLetter) {
        char[] lowerCaseLetters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] lowerCaseSymbols = new char[]{'Љ', '҈', '☹', '∜', '‱', '₪', 'ф', 'Җ', 'ᖎ', 'ᵽ', 'औ', 'Ѧ', '⇊', '⁂', 'Ƃ', 'Ԗ', 'Ѥ', '℃', '※', 'ᵫ', '¿', '®', '₡', '∖', '₢', '≷'};

        for(int i = 0; i < lowerCaseLetters.length; ++i) {
            if (lowerCaseLetter == lowerCaseLetters[i]) {
                int formula = (i - 2) % 25;
                if (formula < 0) {
                    formula += 25;
                    this.cipher = lowerCaseSymbols[formula];
                } else {
                    this.cipher = lowerCaseSymbols[formula];
                }
            }
        }

        return this.cipher;
    }

    public char encryptPunctuationMark(char punctuation) {
        char[] punctMarks = new char[]{'[', '}', '$', '%', '&', '.', '@', ')', '>', '{', '^', '\'', '"', ';', ':', '=', '/', '\\', '!', '*', '-', '_', '+', ',', '~', '#', '|', '?', '`'};
        char[] punctSymbols = new char[]{'⌆', '⌧', '⌨', '⌬', '␦', '⑄', '⑃', '╗', '╙', '╖', '▛', '▜', '▚', '▞', '▷', '◈', '◉', '◕', '◬', '☀', '╬', '☈', '☄', '☫', '☬', '☭', '☹', '♅', '♡'};

        for(int i = 0; i < punctMarks.length; ++i) {
            if (punctuation == punctMarks[i]) {
                int formula = (i - 2) % 28;
                if (formula < 0) {
                    formula += 28;
                    this.cipher = punctSymbols[formula];
                } else {
                    this.cipher = punctSymbols[formula];
                }
            }
        }

        return this.cipher;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String mess) {
        this.message = mess;
    }
}
