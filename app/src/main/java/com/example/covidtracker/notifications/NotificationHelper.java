package com.example.covidtracker.notifications;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.covidtracker.R;
import com.example.covidtracker.classes.Count;
import com.example.covidtracker.classes.Results;
import com.example.covidtracker.data.RetrofitObjectAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.covidtracker.fragments.IndiaFragment.baseURL;
import static com.example.covidtracker.notifications.AlertReceiver.INDIA_COUNT;
import static com.example.covidtracker.notifications.AlertReceiver.WORLD_COUNT;

public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Cases Update")
                .setContentText("World Cases: " + WORLD_COUNT + ", India Cases:  " + INDIA_COUNT)
                .setSmallIcon(R.drawable.corona);
    }
}
