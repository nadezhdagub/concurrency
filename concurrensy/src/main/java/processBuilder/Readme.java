package processBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Readme   {
    public static void shoeReadme() {
        ProcessBuilder pb = new ProcessBuilder();
        try {
            Process p =  pb.command("notepad.exe", "ProcessBuilder.Readme.txt").start();
            p.waitFor();
            System.out.println("ProcessBuilder.Readme has been read");

            Process pr =  pb.command("java.exe", "-version").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line = reader.readLine();
            System.out.println(line);
            pr.waitFor();
            System.out.println("ProcessBuilder.Readme has been read");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
