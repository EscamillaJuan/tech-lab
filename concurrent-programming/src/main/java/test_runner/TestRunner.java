package test_runner;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class TestRunner {

    private record Result(String testName, boolean passed) {
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: TestRunner [test folder] [test name]");
            return;
        }

        Instant programStart = Instant.now();
        List<Method> testMethods = new ArrayList<>();
        Class<?> testClass = getTestClass(args[0], args[1]);

        for (Method method : testClass.getDeclaredMethods()) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                testMethods.add(method);
            }
        }


        List<String> passed = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Result>> results = new ArrayList<>(testMethods.size());

        for (Method method: testMethods) {
            Future<Result> result = executor.submit(() -> {
                try {
                    UnitTest test = (UnitTest) testClass.getConstructor().newInstance();
                    test.beforeEachTest();
                    method.invoke(test);
                    test.afterEachTest();
                    return new Result(getTestName(testClass, method), true);
                } catch (Throwable throwable) {
                    return new Result(getTestName(testClass, method), false);
                }
            });
            results.add(result);
        }

        for (Future<Result> future : results) {
            Result result = future.get();
            if (result.passed) {
                passed.add(result.testName);
            } else {
                failed.add(result.testName);
            }
        }
        executor.shutdown();

        Duration executionTime = Duration.between(programStart, Instant.now());

        System.out.println("Passed tests: " + passed);
        System.out.println("FAILED tests: " + failed);
        System.out.println("Test execution took " + executionTime.toSeconds() + " seconds(s)");
    }

    private static Class<?> getTestClass(String testFolder, String className) throws Exception {
        URL testDir = Path.of(testFolder).toUri().toURL();
        URLClassLoader loader = new URLClassLoader(new URL[]{testDir});
        Class<?> klass = Class.forName(className, true, loader);
        if (!UnitTest.class.isAssignableFrom(klass)) {
            throw new IllegalArgumentException("Class " + klass.toString() + " must implement UnitTest");
        }
        return klass;
    }

    private static String getTestName(Class<?> klass, Method method) {
        return klass.getName() + "#" + method.getName();
    }
}