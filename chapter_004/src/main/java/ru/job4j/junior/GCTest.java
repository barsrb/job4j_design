package ru.job4j.junior;

public class GCTest {
    public static void main(String[] args) {
        User u1 = new User("user1");
        info("User 1 created");
        User u2 = new User("user2");
        info("User 2 created");
        User u3 = new User("user3");
        info("User 3 created");
        System.gc();
        info("Call GC");

        u1 = null;
        u2 = null;
        u3 = null;
        System.gc();
        info("GC after all users = null");

        u1 = new User("user 1");
        u1 = null;
        System.out.println("u1 - null");

        u2 = new User("user 2");
        u2 = null;
        System.out.println("u2 - null");

        u3 = new User("user 3");
        u3 = null;
        System.out.println("u3 - null");

        User u4 = new User("user 4");
        u4 = null;
        System.out.println("u4 - null");

        User u5 = new User("user 5");
        u5 = null;
        System.out.println("u5 - null");

        User u6 = new User("user 6");
        u6 = null;
        System.out.println("u6 - null");

        User u7 = new User("user 7");
        u7 = null;
        System.out.println("u7 - null");




        System.out.println("Done");
    }

    private static void info(String s) {
        int mb = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();

        System.out.println("Heap statistic after action: " + s);

        System.out.println("Used memory  : " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free memory  : " + runtime.freeMemory() / mb);
        System.out.println("Total memory : " + runtime.totalMemory() / mb);
        System.out.println("Max memory   : " + runtime.maxMemory() / mb);
        System.out.println();
    }


}

class User {
    double[] val;
    String s;

    public User(String s) {
        this.s = s;
        val = new double[128 * 1024]; //1mb
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalized " + s);
    }
}