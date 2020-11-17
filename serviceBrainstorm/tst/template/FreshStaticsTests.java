package template;

import java.lang.reflect.*;
import java.net.*;
import java.util.regex.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.*;

@SuppressWarnings("unused")
public class FreshStaticsTests {
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
            String klassName = getClass().getName();
            String klassFile = '/' + klassName.replace('.', '/') + ".class";
            URL    rawLoc = getClass().getResource(klassFile);
            String file   = rawLoc.getFile();
            URL    dirLoc = file.endsWith(".class") ? new URL("file:" + file.replace(klassFile, "/")) : rawLoc;

            URLClassLoader testClassLoader = new URLClassLoader(new URL[]{dirLoc}) {
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
