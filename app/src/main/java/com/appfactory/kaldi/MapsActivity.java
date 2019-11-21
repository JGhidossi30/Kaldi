package com.appfactory.kaldi;



import androidx.fragment.app.FragmentActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, LocationListener
{
    // Global Variables
    private FusedLocationProviderClient mFusedLocationClient;
    double latitude;
    double longitude;
    private Location location;
    private Location dest = null;
    private com.google.maps.model.LatLng destination;
    private GoogleMap mMap;
    private Marker marker;
    private String TAG = "logoutput";
    private GeoApiContext mGeoApiContext = null;

    private ArrayList<String> business;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if(mGeoApiContext == null)
        {
            mGeoApiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R .string.google_maps_key)).build();
        }
        // Continue editing code
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    /* Calculates the directions for the marker we click on */
    private void calculateDirections(Marker marker)
    {
        Log.d(TAG, "calculateDirections: calculating directions.");
        destination = new com.google.maps.model.LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        DirectionsApiRequest direction = new DirectionsApiRequest(mGeoApiContext);
        direction.alternatives(true);
        direction.origin(new com.google.maps.model.LatLng(location.getLatitude(),location.getLongitude()));
        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/My+Location/" + destination.toString()));
        startActivity(browserIntent);
    }


    // Converts a street address to a geolocation address
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try
        {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null)
            {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return p1;
    }

    // Gets the current location of the user
    private void getCurrentLoc()
    {
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation (LocationManager.GPS_PROVIDER);
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        CameraUpdate loc = CameraUpdateFactory.newLatLngZoom(latLng, 15.5f);
        mMap.animateCamera(loc);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
    }

    // Adds marker on map using LatLng object
    void addMarker(String businessName, LatLng latlng, int tag)
    {
        marker = mMap.addMarker(new MarkerOptions().position(latlng).title(businessName).snippet("Come have some delicious coffee!"));
        marker.setTag(tag);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        getCurrentLoc();
        // ---- Repeat process for all added business ---
        String strAddress = "941 Bloom Walk";
        String businessName = "Sal Coffee";
        LatLng latLng = getLocationFromAddress(this, strAddress);
        addMarker(businessName, latLng, 0);
        // --- End of repeating process ---
        Log.d("latlng2", latLng.latitude + "," +latLng.longitude);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker)
    {
        LatLng latLng = marker.getPosition();

        return false;
    }

    @Override
    public void onInfoWindowClick(final Marker marker)
    {
        if(marker.getSnippet().equals("This is you"))
        {
            marker.hideInfoWindow();
        }
        else{

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please make your selection")
                    .setCancelable(true)
                    .setPositiveButton("Directions", new DialogInterface.OnClickListener()
                    {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                        {
                            calculateDirections(marker);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Menu", new DialogInterface.OnClickListener()
                    {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id)
                        {
                            Intent myIntent = new Intent(MapsActivity.this, Menu_Activity.class);
                            startActivityForResult(myIntent, 0);
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @Override
    public void onLocationChanged(Location location)
    {
        longitude = location.getLongitude();
        Log.d("lon", "" + longitude);
        latitude = location.getLatitude();
        Log.d("lat", "" + latitude);
        boolean test = dest != null;
        Log.d("comp", "" + test);
        if(destination != null)
        {
            Location dest = new Location("");
            dest.setLatitude(destination.lat);
            dest.setLongitude(destination.lng);
            float distance =  location.distanceTo(dest);
            Log.d("dist", "" + distance);
            if (distance < 5)
            {
                Intent myIntent = new Intent(MapsActivity.this, Menu_Activity.class);
                startActivityForResult(myIntent, 0);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}
