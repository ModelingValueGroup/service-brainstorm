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

package staticsInSandboxDemo;

import java.lang.reflect.*;
import java.net.*;
import java.util.regex.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.*;

@SuppressWarnings("unused")
public class SandboxedStaticsTests {
    private static int n;

    public void test1() {
        Assertions.assertEquals(0, n);
        n = 4711;
        Assertions.assertEquals(4711, n);
    }

    public void test2() {
        Assertions.assertEquals(0, n); // with classloader the value of previous test is back to default
        n = 420;
        Assertions.assertEquals(420, n);
    }

    @Test
    public void test3() {
        Assertions.assertEquals(0, n);
        n = 4711;
        Assertions.assertEquals(4711, n);
    }

    @Test
    public void test4() {
        Assertions.assertEquals(4711, n); // without classloader the value of previous test is left in static
        n = 420;
        Assertions.assertEquals(420, n);
    }

    @TestFactory
    public Stream<DynamicNode> staticTests() {
        String classNamePattern = Pattern.quote(getClass().getPackageName() + ".") + ".*";
        return Stream.of(
                dynamicTestInIsolation("test1", classNamePattern),
                dynamicTestInIsolation("test2", classNamePattern)
        );
    }

    private DynamicTest dynamicTestInIsolation(String testName, String classNamePattern) {
        try {
            final Class<?> myClass   = getClass();
            String         klassName = myClass.getName();
            String         klassFile = '/' + klassName.replace('.', '/') + ".class";
            URL            rawLoc    = myClass.getResource(klassFile);
            String         file      = rawLoc.getFile();
            URL            dirLoc    = file.endsWith(".class") ? new URL("file:" + file.replace(klassFile, "/")) : rawLoc;
            System.err.println("@@@@ rawLoc of " + myClass.getName() + " = " + rawLoc + " => using " + dirLoc + " for classloader");

            URLClassLoader testClassLoader = new URLClassLoader(new URL[]{dirLoc}, myClass.getClassLoader()) {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    Class<?> c;
                    if (!name.matches(classNamePattern)) {
                        c = super.loadClass(name);
                    } else {
                        synchronized (getClassLoadingLock(name)) {
                            c = findLoadedClass(name);
                            if (c == null) {
                                c = findClass(name);
                            }
                        }
                    }
                    return c;
                }
            };
            Object testObject = testClassLoader.loadClass(klassName).getDeclaredConstructor().newInstance();
            Executable exec = () -> {
                try {
                    testClassLoader.loadClass(klassName).getDeclaredMethod(testName).invoke(testObject);
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionFailedError) {
                        throw e.getCause();
                    }
                    Assertions.fail(e);
                }
            };
            return DynamicTest.dynamicTest(testName, exec);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | MalformedURLException | InvocationTargetException e) {
            Assertions.fail(e);
        }
        return null;
    }
}
