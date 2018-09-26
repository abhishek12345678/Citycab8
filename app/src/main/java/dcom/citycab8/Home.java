package dcom.citycab8;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.widget.Toast;

public class Home extends AppCompatActivity {



    boolean isDriverFound=false;
    String driverId="";
    int radius= 1; // 1 km
    int distance=1; // 3 km
    private static final int LIMIT= 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findDriver();
        loadAllAvailableDriver();
    }

    private void loadAllAvailableDriver() {


        DatabaseReference driverLocation=FirebaseDatabase.getInstance().getReference("Drivers");
        GeoFire gf= new GeoFire(driverLocation);
        GeoQuary geoQuary=gf.queryAtLocation(new Geolocation(mLastLocation.getLatitude(),mLastLocation.getLongitude()),
                distance);
        geoQuary.removeAllListeners();

        geoQuary.addGeoQueryEventListener(new GeoQueryEventListener


                public void onKeyEntered(String key, GeoLocation location)
        {
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(key)
                    .addListenerForSingleValueEvent(new valueEventListener()(
                            public void onDataChange(DataSnapshot dataSnapshot)
            {
                  Rider rider=dataSnapshot.gerValue(Rider.class);

                  mMap.addMarker(new MarkerOptions()
                       .postion(new LagLng(location.latitude,location.longitude))
                       .flat(true)
                        .title(rider.getPhone())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
            }
                )

        }


                privat void onGeoQuaryReady()
        {

            if(distance <= LIMIT)
            {
                distance++;
                loadAllAvailableDriver();

             }
        }


    }

    private void findDriver() {

        DatabaseReference driver=FirebaseDatabase.getInstance().getReference("Drivers");
        GeoFire gfDrivers= new GeoFire(drivers);

        GeoQuary geoQuary=gfDrivers.queryAtLocation(new Geolocation(mLastLocation.getLatitude(),mLastLocation.getLongitude()),
                radius);

        geoQuary.removeAllListeners();
        geoQuary.addGeoQueryEventListener(new GeoQueryEventListener)

                void onKeyEntered()
                {
                    if(!isDriverFound)
                    {
                        isDriverFound = true;
                        driverId = key;
                        btnPickupRequest.setText("Call user");
                        Toast.makeText(Home.this," "+key,Toast.LENGTH_LONG).show();
                    }
                }


         void       onGeoQuary()
        {
           if(!isDriverFound)
           {
               radius++;
               findDriver();
           }
        }
    }
}
