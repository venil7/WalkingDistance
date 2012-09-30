package co.uk.darkruby.WalkingDistance;

import co.uk.darkruby.WalkingDistance.R;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private int toastShows = 0;
	private LocationManager locationManager;
	private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void Button1ClickHandler(View view) {
    	/*locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    	if (locationManager != null) {
    		message("location manager created: " + locationManager.toString());
    	}*/
    	Boolean gpsEnabled = gpsEnabled();
    	message("Gps " + (gpsEnabled
    			? "enabled"
    			: "disabled"));
    	
    	if (gpsEnabled) {
    		this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    		this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
    				5000, 10, new DefaultLocationListener());
    	}
    }
    
    private void message(String text) {
	  int duration = Toast.LENGTH_SHORT;
	  Toast toast = Toast.makeText(context, text, duration);
	  toast.show();
    }
    
    private Boolean gpsEnabled() {  
	  ContentResolver contentResolver = context.getContentResolver();  
	  boolean gpsStatus 
	  	= Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);  
	  return gpsStatus;
    }
}
