package tk.markmota.mapa;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    // Declaro las variables a ocupar
    Button bmapa;
    Button bhibrido;
    Button bterreno;
    Button binterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Casteo de botones, lo que me devuelve no es un boton hay que convertirlo
        bmapa=(Button) findViewById(R.id.boton_mapa);
        bterreno=(Button) findViewById(R.id.boton_terreno);
        bhibrido=(Button) findViewById(R.id.boton_hibrido);
        binterior=(Button) findViewById(R.id.boton_interiores);

        // Ahora relacionamos los Listener a cada boton
        bmapa.setOnClickListener(this);
        bterreno.setOnClickListener(this);
        bhibrido.setOnClickListener(this);
        binterior.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boton_mapa:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.boton_terreno:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.boton_hibrido:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.boton_interiores:
                LatLng mus911 = new LatLng(38.8887861,-77.02602289999999);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        mus911,20
                ));
                mMap.addMarker(new MarkerOptions().position(mus911).title("Marker in Instituto Smithsoniano"));

                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng cdmx = new LatLng(19.4326077, -99.13320799999997);
        mMap.addMarker(new MarkerOptions().position(cdmx).title("Marker in Mexico City Cons"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cdmx));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
                @Override
                public void onMapLongClick(LatLng latLng){
                    mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                    .anchor(0.0f,1.0f)
                    .position(latLng));

                };
        });
    }
}
