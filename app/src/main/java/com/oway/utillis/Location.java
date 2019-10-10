package com.oway.utillis;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

public class Location implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener /*implements LocationListener*/ {
    public static final int REQUEST_CHECK_SETTINGS = 5005;
    private GoogleApiClient client;
    private LocationRequest request;
    private OnLocationChangeListener listener;
    private OnLocationSatiListener onLocationSatiListener;
    //  private  Location instance;
    private Context mContext;

    /*  public  Location getInstance(Context context) {
          mContext = context;
        //  if (instance == null)
              instance = new Location(context);
          return instance;
      }
  */
    public Location(Context context) {
        mContext = context;
        client = new GoogleApiClient.Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        request = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(1000).setFastestInterval(5000).setSmallestDisplacement(200);
    }

    public void setOnLocationChangeListener(OnLocationChangeListener l, OnLocationSatiListener onLocationSatiListener) {
        this.listener = l;
        this.onLocationSatiListener = onLocationSatiListener;
    }

    public boolean isStarted() {
        return client.isConnected();
    }

    public Location setup() {
        client.connect();
        return this;
    }

    public LatLng getLocation() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the User grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        android.location.Location loc = LocationServices.FusedLocationApi.getLastLocation(client);
        return loc == null ? null : new LatLng(loc.getLatitude(), loc.getLongitude());
    }

    public Location unregister() {
        if (client.isConnected())
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        return this;
    }

    public void stopGoogleClient() {
        if (client != null && client.isConnected())
            client.disconnect();
    }

    public void reConnectGoogleClient() {
        if (client != null && !client.isConnected())
            client.connect();
    }

    public void resumeLocationupdates() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the User grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                client, request, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the User grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(request);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(client, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //   final LocationSettingsStates = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        if (onLocationSatiListener != null)
                            onLocationSatiListener.onLocationSatisfied();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        // Location settings are not satisfied. But could be fixed by showing the User
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    (Activity) mContext,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });

    }

    @Override
    public void onConnectionSuspended(int i) {
        client.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        if (listener != null)
            listener.onLocationChanged(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    public interface OnLocationChangeListener {
        void onLocationChanged(LatLng location);
    }

    public interface OnLocationSatiListener {
        void onLocationSatisfied();
    }

    public boolean isProviderEnabled(Context context) {
        final android.location.LocationManager manager = (android.location.LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return !(!manager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) && !manager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER));
    }
}