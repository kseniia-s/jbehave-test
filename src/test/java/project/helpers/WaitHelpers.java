package project.helpers;

import project.settings.Browser;

public class WaitHelpers {

    public static void waitDefaultTimeToWait()  {
        try {
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
