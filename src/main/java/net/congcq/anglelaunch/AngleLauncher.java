package net.congcq.anglelaunch;

import java.io.*;

import javafx.application.Application;

import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;

public class AngleLauncher extends UIApplicationDelegateAdapter {

    @Override
    public boolean didFinishLaunching(UIApplication application,
        UIApplicationLaunchOptions launchOptions) {
        
        Thread launchThread = new Thread() {
            @Override
            public void run() {
                Application.launch(AngleLaunchJFXApp.class);
            }
        };
        launchThread.setDaemon(true);
        launchThread.start();

        return true;
    }
    
    public static void main(String[] args) {
/*
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                pw.flush();
                System.err.println("UNCAUGHT EXCEPTION: " + sw.toString());

//                Platform.getPlatform().runOnUIThread(() -> {
//                    WindowAlertController alertController = new WindowAlertController("Error", sw.toString(), UIAlertControllerStyle.Alert);
//                    alertController.addAction(new UIAlertAction("OK",
//                        UIAlertActionStyle.Default, (action) -> {
//                            alertController.didDismiss();
//                        }
//                    ));
//                    alertController.show();  
//                });

            }
        });
        
        try {
            PrintStream filePrintStream = new PrintStream(new FileOutputStream(System.getenv("HOME") + "/log_output.txt"));
            System.setOut(filePrintStream);
            System.setErr(filePrintStream);

            System.out.println("Starting UI...");
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
*/

        System.setProperty("java.library.path", System.getProperty("java.home") + "/Frameworks");

        System.setProperty("javafx.verbose", "true");
        System.setProperty("prism.verbose", "true");
        System.setProperty("glass.platform", "ios");

        System.out.println("Starting UI...");
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, AngleLauncher.class);
        pool.drain();
    }
}