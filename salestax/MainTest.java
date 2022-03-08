package salestax;

import java.io.*;

public class MainTest {
    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("correct_output.txt"));

        StringBuffer sb = new StringBuffer();

        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        Main.main(null);

        String actual = Main.printInfo();

        if (!actual.equals(sb.toString())) {
            System.out.println("test failed");
        }
        else {
            System.out.println("test passed");
        }

    }
}
