package co.edu.icesi.innlab.cacaoapp;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("FIREBASE BODY", remoteMessage.getNotification().getBody());
        Log.e("FIREBASE TITLE", remoteMessage.getNotification().getTitle());
    }
}