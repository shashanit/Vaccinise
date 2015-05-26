package com.example.vaccinator;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

public class MainActivity extends Activity {

	int fdate;
	int app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 10 may 2015
		// Initital values to check day of year

		 switch(fdate)
		  {
		  case 0 : Notification notia = new Notification.Builder(this)
			.setContentTitle("Vaccination Due today")
			.setContentText("1.BCG 2.Polio 3.Hepatitis" + app )
			.setSmallIcon(R.drawable.ic_launcher).build();
		  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			// hide the notification after its selected
			notia.flags |= Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify(0, notia);
		  case 45: Notification botia = new Notification.Builder(this)
			.setContentTitle("Vaccination Due today")
			.setContentText("45 days wala medicine" + app )
			.setSmallIcon(R.drawable.ic_launcher).build();
		  NotificationManager botificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			// hide the notification after its selected
			botia.flags |= Notification.FLAG_AUTO_CANCEL;
			botificationManager.notify(0, botia);
		  case 60: 
			  Notification lotia = new Notification.Builder(this)
				.setContentTitle("Vaccination Due today")
				.setContentText("45 days wala medicine" + app )
				.setSmallIcon(R.drawable.ic_launcher).build(); 	
			  NotificationManager lotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				// hide the notification after its selected
				lotia.flags |= Notification.FLAG_AUTO_CANCEL;
				lotificationManager.notify(0, lotia);
		  
		  }
		  Calendar mal=Calendar.getInstance(); 
		  int app=mal.get(Calendar.DAY_OF_MONTH);
		  
		  mal.set(Calendar.HOUR_OF_DAY,10); 
		  mal.set(Calendar.MINUTE,0);
		  mal.set(Calendar.SECOND,0); 
		  
		  Intent intent = new Intent(MainActivity.this, MainActivity.class);
		  PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
		  ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, mal.getTimeInMillis(), pendingIntent);
		 
		  	Notification notia = new Notification.Builder(this)
					.setContentTitle("Vaccination Due today")
					.setContentText("The day is " + fdate )
					.setSmallIcon(R.drawable.ic_launcher).build();
		  	
		  	
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			// hide the notification after its selected
			notia.flags |= Notification.FLAG_AUTO_CANCEL;
			notificationManager.notify(0, notia);

	}

	public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getFragmentManager(), "DatePicker");
	}

	public class SelectDateFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		@Override
		public void onDateSet(DatePicker view, int yearb, int monthb, int dayb) {

			int age_days = 0;

			// gets the day of the year where january 1 is the 1st day
			// december 31 is 365 day
			Calendar age2days = Calendar.getInstance();
			age2days.set(Calendar.YEAR, yearb);
			age2days.set(Calendar.MONTH, monthb);
			age2days.set(Calendar.DAY_OF_MONTH, dayb);
			age_days = age2days.get(Calendar.DAY_OF_YEAR);

			// Gets the current year and day of the year

			int age_daysc = 0;
			int yearc = 0;
			Calendar cur2day = Calendar.getInstance();
			yearc = cur2day.get(Calendar.YEAR);
			age_daysc = cur2day.get(Calendar.DAY_OF_YEAR);

			int yearf = 0;
			int dayf = 0;

			int i = 0; // intitalization for loop
			int ldays = 0;// Keeps a count of number of extra days

			// Logic to find the number of extra days due to leap year.
			for (i = yearb; i <= yearc; i++) {
				if (i % 4 == 0 && i % 100 != 0) {
					ldays++;

				} else if (i % 4 == 0 && i % 100 == 0) {
					ldays++;
				}

			}
			// Logic to find the age in days.
			yearf = (yearc - yearb) * 365;
			dayf = age_daysc - age_days;
			if (dayf <= 0) {
				if (dayf < 0)
					dayf = 365 - (age_days - age_daysc);
				yearf = yearf - 365;
			}

			// final age in days
			fdate = yearf + dayf + ldays;
			notifier();

		}

	}

	public void notifier() {

		Notification noti = new Notification.Builder(this)
				.setContentTitle("Vaccination Due today")
				.setContentText("The age is " + fdate )
				.setSmallIcon(R.drawable.ic_launcher).build();

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, noti);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}