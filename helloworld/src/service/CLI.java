package service;

import java.io.*;

public class CLI {
    public static void execute(String command) {
        try {
            Runtime run    = Runtime.getRuntime();
            Process pr     = run.exec(command);
            Slurp   slurp1 = new Slurp(pr.getInputStream());
            Slurp   slurp2 = new Slurp(pr.getErrorStream());
            int     rc     = pr.waitFor();
            if (rc != 0) {
                System.err.println("CLI command returned " + rc);
                System.err.println("    command = " + command);
                System.err.println("    stdout  = " + slurp1);
                System.err.println("    stderr  = " + slurp2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class Slurp extends Thread {
        private BufferedReader buf;
        private StringBuilder  b = new StringBuilder();

        public Slurp(InputStream is) {
            buf = new BufferedReader(new InputStreamReader(is));
            start();
        }

        @Override
        public void run() {
            try {
                for (String line; ((line = buf.readLine()) != null); ) {
                    b.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return b.toString();
        }
    }
}