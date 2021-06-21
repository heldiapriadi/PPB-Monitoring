package com.ppb.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationJobService extends JobService {
    NotificationManager mNotifyManager;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    private static  ExecutorService executor;

    @Override
    public boolean onStartJob(JobParameters params) {
        //Create the notification channel
//        createNotificationChannel();
//
//        //Set up the notification content intent to launch the app when clicked
//        PendingIntent contentPendingIntent = PendingIntent.getActivity
//                (this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder
//                (this, PRIMARY_CHANNEL_ID)
//                .setContentTitle("Job Service")
//                .setContentText("Your Job ran to completion!")
//                .setContentIntent(contentPendingIntent)
//                .setSmallIcon(R.drawable.ic_job_running)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setAutoCancel(true);

//        Runnable runnable = () -> {
//            try {
////                String name = Thread.currentThread().getName();
////                System.out.println("Foo " + name);
//                TimeUnit.SECONDS.sleep(5);
////                System.out.println("Bar " + name);
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//
//        runnable.run();

//        Runnable runnable = new Runnable() {
//            public void run() {
//                // Do something
//            }
//        };
//        worker.schedule(runnable, 10, TimeUnit.SECONDS);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        jobFinished(params, true);
        executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                boolean finish = true;
                try {
                    System.out.println("attempt to shutdown executor");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("done");
                }
                catch (InterruptedException e) {
                    System.err.println("tasks interrupted");
                    finish = false;
                }finally {
                    jobFinished(params,!finish);
                }
            }
        });
//        executor.submit(() -> {
//            boolean finish = true;
//            try {
//                System.out.println("attempt to shutdown executor");
//                TimeUnit.SECONDS.sleep(5);
//                System.out.println("done");
//            }
//            catch (InterruptedException e) {
//                System.err.println("tasks interrupted");
//                finish = false;
//            }finally {
//                jobFinished(params,!finish);
//            }
//        });

//        mNotifyManager.notify(0, builder.build());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        System.err.println("onstop");
        if (executor != null){
            executor.shutdownNow();
            System.err.println("Stop");
            Toast.makeText(this, "Job Gagal",
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Define notification manager object.
        mNotifyManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Job Service notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifications from Job Service");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
}
