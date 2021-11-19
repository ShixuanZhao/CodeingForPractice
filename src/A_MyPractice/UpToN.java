package A_MyPractice;

public class UpToN {
    public String string1UpToN(int n) {
        return RAppendUpTo("", 1, n);
    }

    private String RAppendUpTo(String strSoFar, int i, int n) {
        if (i == n) {
            strSoFar += i;
            return strSoFar;
        }
        strSoFar += (i + " " + RAppendUpTo(strSoFar, i + 1, n));
        return strSoFar;
    }

    public static void main(String[] args) {
        UpToN test = new UpToN();
        System.out.println(test.string1UpToN(5));
    }
}

