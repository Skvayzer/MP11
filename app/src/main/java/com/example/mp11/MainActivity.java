package com.example.mp11;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import link.fls.swipestack.SwipeStack;
//
//
//public class MainActivity extends AppCompatActivity {
//    ArrayList<String> list=new ArrayList<String>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        list.add("Соня");
//        list.add("Ты");
//        list.add("Чукча");
//        SwipeStack swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
//        swipeStack.setAdapter(new SwipeStackAdapter(list, this));
//
//    }
//}


import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity implements CardFragment.OnFragmentInteractionListener,
        SocialFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, VideoPlayerFragment.OnFragmentInteractionListener, DictionariesFragment.OnFragmentInteractionListener  {

//    private Button mButtonLeft, mButtonRight, showbtn;
//    private FloatingActionButton mFab;
//
//    private ArrayList<String> mData;
//    private SwipeStack mSwipeStack;
//    private SwipeStackAdapter mAdapter;
//    private TextView word, anword;
//    boolean visibility=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mSwipeStack = (SwipeStack) findViewById(R.id.swipeStack);
//        mButtonLeft = (Button) findViewById(R.id.buttonSwipeLeft);
//        mButtonRight = (Button) findViewById(R.id.buttonSwipeRight);
//        mFab = (FloatingActionButton) findViewById(R.id.fabAdd);
//        showbtn=(Button)findViewById(R.id.showwordbtn);
//        word=(TextView) findViewById(R.id.textViewCard) ;
//        anword=(TextView) findViewById(R.id.textViewCardanother);
//
//        mButtonLeft.setOnClickListener(this);
//        mButtonRight.setOnClickListener(this);
//        mFab.setOnClickListener(this);
//
//        mData = new ArrayList<>();
//        mAdapter = new SwipeStackAdapter(mData);
//        mSwipeStack.setAdapter(mAdapter);
//        mSwipeStack.setListener(this);
//
//        showbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!visibility){
//                    anword.setVisibility(View.VISIBLE);
//                    word.setVisibility(View.INVISIBLE);
//
//                }else{
//                    anword.setVisibility(View.INVISIBLE);
//                    word.setVisibility(View.VISIBLE);
//                }
//                visibility=!visibility;
//            }
//        });
//
//        fillWithTestData();

        FragmentManager fragmentManager = getFragmentManager();


        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_social:
                                selectedFragment = SocialFragment.newInstance("kek", "lol");
                                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                                break;
                            case R.id.navigation_dictionaties:
                                selectedFragment = DictionariesFragment.newInstance("kek","lol");
                                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                                break;
                            case R.id.navigation_cards:
                                selectedFragment = CardFragment.newInstance("kek", "lol");
                                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                                break;
                            case R.id.navigation_video:
                                selectedFragment = VideoPlayerFragment.newInstance("kek","lol");
                                bottomNavigationView.getMenu().getItem(3).setChecked(true);
                                break;
                            case R.id.navigation_settings:
                                selectedFragment = SettingsFragment.newInstance("kek","lol");
                                bottomNavigationView.getMenu().getItem(4).setChecked(true);
                                break;


                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.navigation_cards, SocialFragment.newInstance("kek","lol"));
        transaction.commit();
        Intent serv=new Intent(getApplicationContext(),EasyWordsBtn.class);
        startService(serv);


//уведомлялки
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //set the notification to repeat every fifteen minutes
        long startTime = 10; // 2 min
        // set unique id to the pending item, so we can call it when needed
        PendingIntent pi = PendingIntent.getBroadcast(this, 001, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime() +
                startTime, 10, pi);



      //  scheduleNotification(getNotification("1 second delay"), 1000);
      //  scheduleNotification(getApplicationContext(),1000,0);
    }
//    private void scheduleNotification(Notification notification, int delay) {
//
//        Intent notificationIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
//        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
//        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }
//
//    private Notification getNotification(String content) {
//        Notification.Builder builder = new Notification.Builder(getApplicationContext());
//        builder.setContentTitle("Scheduled Notification");
//        builder.setContentText(content);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        return builder.build();
//    }


//    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setContentTitle("hey")
//                .setContentText("work")
//                .setAutoCancel(true)
//                .setSmallIcon(R.mipmap.ic_launcher_round);
//
//                builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
//
//        Intent intent = new Intent(context, MainActivity.class);
//        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        builder.setContentIntent(activity);
//
//        Notification notification = builder.build();
//
//        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
//        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, notificationId);
//        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION, notification);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        long futureInMillis = SystemClock.elapsedRealtime() + delay;
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
//    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//    private void fillWithTestData() {
//        for (int x = 0; x < 5; x++) {
//            mData.add(getString(R.string.dummy_text) + " " + (x + 1));
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.equals(mButtonLeft)) {
//            mSwipeStack.swipeTopViewToLeft();
//        } else if (v.equals(mButtonRight)) {
//            mSwipeStack.swipeTopViewToRight();
//        } else if (v.equals(mFab)) {
//            mData.add(getString(R.string.dummy_fab));
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        switch (item.getItemId()) {
////            case R.id.menuReset:
////                mSwipeStack.resetStack();
////                Snackbar.make(mFab, R.string.stack_reset, Snackbar.LENGTH_SHORT).show();
////                return true;
////            case R.id.menuGitHub:
////                Intent browserIntent = new Intent(
////                        Intent.ACTION_VIEW, Uri.parse("https://github.com/flschweiger/SwipeStack"));
////                startActivity(browserIntent);
////                return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onViewSwipedToRight(int position) {
//        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, getString(R.string.view_swiped_right)+ swipedElement,
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onViewSwipedToLeft(int position) {
//        String swipedElement = mAdapter.getItem(position);
//        Toast.makeText(this, getString(R.string.view_swiped_left)+ swipedElement,
//                Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStackEmpty() {
//        Toast.makeText(this, R.string.stack_empty, Toast.LENGTH_SHORT).show();
//    }
//
//    public class SwipeStackAdapter extends BaseAdapter {
//
//        private List<String> mData;
//
//        public SwipeStackAdapter(List<String> data) {
//            this.mData = data;
//        }
//
//        @Override
//        public int getCount() {
//            return mData.size();
//        }
//
//        @Override
//        public String getItem(int position) {
//            return mData.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
//            }
//
//            TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
//            textViewCard.setText(mData.get(position));
//
//            return convertView;
//        }
//    }


}
