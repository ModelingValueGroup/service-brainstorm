//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2020 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
//                                                                                                                     ~
// Licensed under the GNU Lesser General Public License v3.0 (the 'License'). You may not use this file except in      ~
// compliance with the License. You may obtain a copy of the License at: https://choosealicense.com/licenses/lgpl-3.0  ~
// Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on ~
// an 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the  ~
// specific language governing permissions and limitations under the License.                                          ~
//                                                                                                                     ~
// Maintainers:                                                                                                        ~
//     Wim Bast, Tom Brus, Ronald Krijgsheld                                                                           ~
// Contributors:                                                                                                       ~
//     Arjan Kok, Carel Bast                                                                                           ~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package simpleservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static List<String> readResource(String name, Class<?> nextTo) {
        List<String> l        = new ArrayList<>();
        String       fullName = nextTo.getPackageName().replace('.', '/') + '/' + name;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(nextTo.getClassLoader().getResourceAsStream(fullName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                l.add(line);
            }
        } catch (IOException e) {
            throw new Error("problem reading " + name, e);
        }
        return l;
    }

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
        private final BufferedReader buf;
        private final StringBuilder  b = new StringBuilder();

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
