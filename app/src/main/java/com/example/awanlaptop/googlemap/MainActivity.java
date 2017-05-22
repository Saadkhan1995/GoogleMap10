package com.example.awanlaptop.googlemap;

import android.app.Dialog;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;


import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Saad onCreate","1");
        super.onCreate(savedInstanceState);
        Log.i("Saad onCreate","2");

        if(googleServicesAvailable()){
            Log.i("Saad oncreate","3");
            Toast.makeText(this, "Perfect", Toast.LENGTH_LONG).show();
            Log.i("Saad oncreate","4");
            setContentView(com.example.awanlaptop.googlemap.R.layout.activity_main);
            Log.i("Saad oncreate","5");
            intiMap();
            Log.i("Saad oncreate","6");
        }else
            {

        }
    }

    private void intiMap() {
        Log.i("Saad intiMap","7");
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        Log.i("Saad  intiMap","8");
        mapFragment.getMapAsync(this);
        Log.i("Saad intiMap","9");
    }

    public boolean googleServicesAvailable(){
        Log.i("Saad googleservice","10");
        GoogleApiAvailability api=GoogleApiAvailability.getInstance();
        Log.i("Saad googleservice","11");
        int isAvailable=api.isGooglePlayServicesAvailable(this);
        Log.i("Saad googleservice","12");
        if(isAvailable== ConnectionResult.SUCCESS){
            Log.i("Saad isavailable","13");
            return true;

        }else if(api.isUserResolvableError(isAvailable))
       {
           Log.i("Saad googleservice","14");
            Dialog dialog=api.getErrorDialog(this,isAvailable,0);
            Log.i("Saad googleservice","15");
            dialog.show();
           Log.i("Saad googleservice","16");
        }else{
            Toast.makeText(this, "Can't connect to play services", Toast.LENGTH_LONG).show();
            Log.i("Saad googleservice","17");
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("Saad onMapReady","18");
        mGoogleMap=googleMap;
        Log.i("Saad onMapReady","19");
        goToLocationZoom(33.547275, 73.189138,15);
        Log.i("Saad onMapReady","20");
    }

    private void goToLocation(double lat, double lng) {
        Log.i("Saad gotolocation","21");
        LatLng ll=new LatLng(lat,lng);
        Log.i("Saad gotolocation","22");
        CameraUpdate update= CameraUpdateFactory.newLatLng(ll);
        Log.i("Saad gotolocation","23");
        mGoogleMap.moveCamera(update);
        Log.i("Saad gotolocation","24");

    }
    private void goToLocationZoom(double lat, double lng,float zoom) {
        Log.i("Saad gotolocationzoom","25");
        LatLng ll=new LatLng(lat,lng);
        Log.i("Saad gotolocationzoom","26");
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);
        Log.i("Saad gotolocationzoom","27");
        mGoogleMap.moveCamera(update);
        Log.i("Saad gotolocationzoom","28");

    }
    public void geoLocate(View view) throws IOException {

        EditText et=(EditText)findViewById(R.id.editText);
        String location=et.getText().toString();
        Geocoder gc=new Geocoder(this);
        List<android.location.Address> list=gc.getFromLocationName(location,1);
        android.location.Address address=list.get(0);
        String locality=address.getLocality();
        Toast.makeText(this,locality,Toast.LENGTH_LONG).show();
        double lat= address.getLatitude();
        double lng=address.getLongitude();
        goToLocationZoom(lat,lng,15);

    }

   // @Override
    //public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.menu,menu);
        //return super.onCreateOptionsMenu(menu);
    //}
}


