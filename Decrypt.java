package classes;

import controllers.PanelController;
import java.util.ArrayList;

public class Decrypt {
    private ArrayList<Character> charArray;
    private ArrayList<Character> cipherArray;
    private char cipher;
    private char cifa;
    private String message;
    private boolean feedBack;
    private final PanelController pc = new PanelController();

    public Decrypt() {
        charArray = new ArrayList();
        cipherArray = new ArrayList();
    }

    public String decrypt(String message) {
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

        for(i = 0; i < this.charArray.size(); ++i) {
            int rawValue = (Character)this.charArray.get(i);
            String uniCodeString = String.format("\\u%04x", Integer.valueOf(rawValue));
            if (this.isNumberSymbol((Character)this.charArray.get(i))) {
                this.cifa = this.decryptNumber(uniCodeString);
            } else if (this.isUpperCaseSymbol((Character)this.charArray.get(i))) {
                this.cifa = this.decryptUpperCase(uniCodeString);
            } else if (this.isLowerCaseSymbol((Character)this.charArray.get(i))) {
                this.cifa = this.decryptLowerCase(uniCodeString);
            } else if (this.isPunctuationSymbol((Character)this.charArray.get(i))) {
                this.cifa = this.decryptPunctuationMark(uniCodeString);
            } else {
                System.out.println("on");
            }

            this.cipherArray.add(this.cifa);
        }

        StringBuffer sb = new StringBuffer();

        for(i = 0; i < this.cipherArray.size(); ++i) {
            sb.append(this.cipherArray.get(i));
        }

        return sb.toString();
    }

    public char decryptNumber(String uniCodeString) {
        char[] numbers = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] numberSymbols = new String[]{"\\u2105", "\\u2109", "\\u2202", "\\u220F", "\\u00B6", "\\u00e6", "\\u0195", "\\u02A4", "\\u03E3", "\\u045E"};

        for(int i = 0; i < numberSymbols.length; ++i) {
            if (uniCodeString.equals(numberSymbols[i])) {
                int formula = (i + 2) % 10;
                if (formula < 0) {
                    formula += 10;
                    this.cipher = numbers[formula];
                } else {
                    this.cipher = numbers[formula];
                }
            }
        }

        System.out.println("Symbol: " + this.cipher);
        return this.cipher;
    }

    public char decryptUpperCase(String uniCodeString) {
        char[] upperCaseLetters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[] upperCaseSymbols = new String[]{"\\u047e", "\\u058d", "\\u0934", "\\u0b87", "\\u1339", "\\u1554", "\\u15b1", "\\u1980", "\\u1984", "\\u1986", "\\u2100", "\\u1987", "\\u198A", "\\u1c53", "\\u0370", "\\u0293", "\\u04b4", "\\u028e", "\\u20df", "\\u215d", "\\u21d7", "\\u2230", "\\u23cf", "\\u2629", "\\u262f", "\\u026e"};

        for(int i = 0; i < upperCaseSymbols.length; ++i) {
            System.out.println(uniCodeString + " VS " + upperCaseSymbols[i]);
            if (uniCodeString.equalsIgnoreCase(upperCaseSymbols[i])) {
                int formula = (i + 2) % 26;
                if (formula < 0) {
                    formula += 26;
                    this.cipher = upperCaseLetters[formula];
                } else {
                    this.cipher = upperCaseLetters[formula];
                }
            }
        }

        System.out.println("Upper Case Symbol: " + this.cipher);
        return this.cipher;
    }

    public char decryptLowerCase(String uniCodeString) {
        char[] lowerCaseLetters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String[] lowerCaseSymbols = new String[]{"\\u0409", "\\u0488", "\\u2639", "\\u221c", "\\u2031", "\\u20AA", "\\u0444", "\\u0496", "\\u158E", "\\u1D7D", "\\u0914", "\\u0466", "\\u21CA", "\\u2042", "\\u0182", "\\u0516", "\\u0464", "\\u2103", "\\u203B", "\\u1D6B", "\\u00BF", "\\u00AE", "\\u20A1", "\\u2216", "\\u20A2", "\\u2277"};

        for(int i = 0; i < lowerCaseSymbols.length; ++i) {
            if (uniCodeString.equals(lowerCaseSymbols[i])) {
                int formula = (i + 2) % 25;
                if (formula < 0) {
                    formula += 25;
                    this.cipher = lowerCaseLetters[formula];
                } else {
                    this.cipher = lowerCaseLetters[formula];
                }
            }
        }

        return this.cipher;
    }

    public char decryptPunctuationMark(String uniCodeString) {
        char[] punctMarks = new char[]{'[', '}', '$', '%', '&', '.', '@', ')', '>', '{', '^', '\'', '"', ';', ':', '=', '/', '\\', '!', '*', '-', '_', '+', ',', '~', '#', '|', '?', '`'};
        String[] punctMarkSymbol = new String[]{"\\u2306", "\\u2327", "\\u2328", "\\u232C", "\\u2426", "\\u2444", "\\u2443", "\\u2557", "\\u2559", "\\u2556", "\\u259B", "\\u259C", "\\u259A", "\\u259E", "\\u25B7", "\\u25C8", "\\u25C9", "\\u25D5", "\\u25EC", "\\u2600", "\\u256C", "\\u2608", "\\u2604", "\\u262B", "\\u262C", "\\u262D", "\\u2639", "\\u2645", "\\u2661"};

        for(int i = 0; i < punctMarks.length; ++i) {
            if (uniCodeString.equals(punctMarks[i])) {
                int formula = (i + 2) % 29;
                if (formula < 0) {
                    formula += 29;
                    this.cipher = punctMarks[formula];
                } else {
                    this.cipher = punctMarks[formula];
                }
            }
        }

        return this.cipher;
    }

    private boolean isUpperCaseSymbol(char symbolCharacter) {
        String uniCodeString = String.format("\\u%04x", Integer.valueOf(symbolCharacter));
        String[] upperCaseSymbols = new String[]{"\\u047E", "\\u058D", "\\u0934", "\\u0B87", "\\u1339", "\\u1554", "\\u15B1", "\\u1980", "\\u1984", "\\u1986", "\\u2100", "\\u1987", "\\u198A", "\\u1C53", "\\u0370", "\\u0293", "\\u04B4", "\\u028E", "\\u20DF", "\\u215D", "\\u21D7", "\\u2230", "\\u23CF", "\\u2629", "\\u262F", "\\u026e"};
        int i = 0;

        do {
            if (uniCodeString.equals(upperCaseSymbols[i])) {
                this.feedBack = true;
                break;
            }

            this.feedBack = false;
            ++i;
        } while(i < upperCaseSymbols.length);

        return this.feedBack;
    }

    private boolean isNumberSymbol(char symbolCharacter) {
        String uniCodeString = String.format("\\u%04x", Integer.valueOf(symbolCharacter));
        String[] numberSymbols = new String[]{"\\u2105", "\\u2109", "\\u2202", "\\u220f", "\\u00b6", "\\u00e6", "\\u0195", "\\u02a4", "\\u03e3", "\\u045e"};
        int i = 0;

        do {
            if (uniCodeString.equals(numberSymbols[i])) {
                this.feedBack = true;
                break;
            }

            this.feedBack = false;
            ++i;
        } while(i < numberSymbols.length);

        return this.feedBack;
    }

    public boolean isLowerCaseSymbol(char symbolCharacter) {
        String uniCodeString = String.format("\\u%04x", Integer.valueOf(symbolCharacter));
        String[] lowerCaseSymbols = new String[]{"\\u0409", "\\u0488", "\\u1533", "\\u1a0e", "\\u2031", "\\u20aa", "\\u0444", "\\u0496", "\\u1555", "\\u1d7d", "\\u0914", "\\u0466", "\\u21ca", "\\u2042", "\\u155c", "\\u07e1", "\\u0464", "\\u2103", "\\u203b", "\\u1d6b", "\\u156f", "\\u1d93", "\\u20A1", "\\u2216", "\\u20a2", "\\u2277"};
        int i = 0;

        do {
            if (uniCodeString.equals(lowerCaseSymbols[i])) {
                this.feedBack = true;
                break;
            }

            this.feedBack = false;
            ++i;
        } while(i < lowerCaseSymbols.length);

        return this.feedBack;
    }

    private boolean isPunctuationSymbol(char symbolCharacter) {
        String uniCodeString = String.format("\\u%04x", Integer.valueOf(symbolCharacter));
        String[] punctSymbols = new String[]{"\\u2306", "\\u2327", "\\u2328", "\\u232C", "\\u2426", "\\u2444", "\\u2443", "\\u2557", "\\u2559", "\\u2556", "\\u259B", "\\u259C", "\\u259A", "\\u259E", "\\u25B7", "\\u25C8", "\\u25C9", "\\u25D5", "\\u25EC", "\\u2600", "\\u256C", "\\u2608", "\\u2604", "\\u262B", "\\u262C", "\\u262D", "\\u2639", "\\u2645", "\\u2661"};
        int i = 0;

        do {
            if (uniCodeString.equals(punctSymbols[i])) {
                this.feedBack = true;
                break;
            }

            this.feedBack = false;
            ++i;
        } while(i < punctSymbols.length);

        return this.feedBack;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String mess) {
        this.message = mess;
    }
}
