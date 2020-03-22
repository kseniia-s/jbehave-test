package project.settings;

import java.util.function.Supplier;

public class ScenarioContext {

    public static void initContext(Context context) {
//        System.out.println(Thread.currentThread().getName()+": initContext");
        userThreadLocal.set(context);
    }

    private static ThreadLocal<Context> userThreadLocal = new ThreadLocal<>();

    public static Context context() {
//        System.out.println(Thread.currentThread().getName()+": getContext");
        return userThreadLocal.get();
    }

}
