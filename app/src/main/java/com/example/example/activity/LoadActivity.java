package com.example.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.example.Manager.Subway;
import com.example.example.Network.MapApi;
import com.example.example.Network.MapClient;
import com.example.example.Network.MapModel.DirectionResponses;
import com.example.example.R;
import com.example.example.databinding.ActivityLoadBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityLoadBinding binding;
    private List<Subway> subwayList ;
    private GoogleMap mMap;
    private List<String> station;

    private String startposition;
    private String endposition;
    private boolean startflag = false;
    private boolean endflag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_load);
        binding.setActivity(this);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        subwayList = new ArrayList<Subway>();
        station = new ArrayList<String>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        String json = "";


        try{
            InputStream is = getAssets().open("subway.json");
            long fileSize = is.available();




            byte[] buffer = new byte[(int)fileSize];
            Log.d("인트값",""+fileSize);
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");

        }catch (IOException ex){
            ex.printStackTrace();
        }

        try{
            JSONObject jsonObject=new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jj = jsonArray.getJSONObject(i);

                Subway subway = new Subway();

                subway.setStation(jj.getString("statn_nm"));
                subway.setAddress(jj.getString("adres"));

                subwayList.add(subway);
                station.add(subwayList.get(i).getStation());
                Log.d("bbbbb",subway.getStation()+"\n"+subway.getAddress());

            }

        }catch (JSONException e){}

        binding.startsub.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,station));
        binding.endsub.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,station));

        binding.startsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //-- 키보드 내리기 --
                InputMethodManager im = (InputMethodManager) getSystemService((INPUT_METHOD_SERVICE));
                im.hideSoftInputFromWindow(binding.startsub.getWindowToken(),0);
                //---------------
                mMap.clear();
                startflag = true;
                String start = binding.startsub.getText().toString();
                for (int i=0;i<subwayList.size();i++){
                    if(start.equals(subwayList.get(i).getStation())){
                        Toast.makeText(LoadActivity.this,subwayList.get(i).getAddress(),Toast.LENGTH_LONG).show();
                        List<Address> list = null;
                        Geocoder geocoder = new Geocoder(LoadActivity.this);
                        try {
                            list = geocoder.getFromLocationName(subwayList.get(i).getAddress(), 10);
                            LatLng addr = new LatLng(list.get(0).getLatitude(),list.get(0).getLongitude());
                            mMap.addMarker(new MarkerOptions().position(addr).title(start));
                            startposition=String.valueOf(addr.latitude)+","+String.valueOf(addr.longitude);
                        }catch (IOException e){}
                    }
                }
            }
        });

        binding.endsub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //-- 키보드 내리기 --
                InputMethodManager im = (InputMethodManager) getSystemService((INPUT_METHOD_SERVICE));
                im.hideSoftInputFromWindow(binding.endsub.getWindowToken(),0);
                //---------------
                endflag = true;
                String start = binding.endsub.getText().toString();
                for (int i=0;i<subwayList.size();i++){
                    if(start.equals(subwayList.get(i).getStation())){
                        Toast.makeText(LoadActivity.this,subwayList.get(i).getAddress(),Toast.LENGTH_LONG).show();
                        List<Address> list = null;
                        Geocoder geocoder = new Geocoder(LoadActivity.this);
                        try {
                            list = geocoder.getFromLocationName(subwayList.get(i).getAddress(), 10);
                            LatLng addr = new LatLng(list.get(0).getLatitude(),list.get(0).getLongitude());
                            mMap.addMarker(new MarkerOptions().position(addr).title(start));
                            endposition=String.valueOf(addr.latitude)+","+String.valueOf(addr.longitude);
                        }catch (IOException e){}
                    }
                }
            }

        });

        binding.directionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startflag && endflag) {
                    MapApi mapApi = MapClient.mapApi(LoadActivity.this);
                    mapApi.getDirections(startposition, endposition, "transit", getString(R.string.direction_key))
                            .enqueue(new Callback<DirectionResponses>() {
                                @Override
                                public void onResponse(Call<DirectionResponses> call, Response<DirectionResponses> response) {
                                    Log.d("경로", startposition + "    " + endposition);
                                    Toast.makeText(LoadActivity.this, "성공", Toast.LENGTH_LONG).show();
                                    Log.d("성공", response.body().getRoutes().size() + "");
                                    drawPolyline(response);
                                    String[] lanlng = startposition.split(",");
                                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
//                              mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(lanlng[0]),
//                                      Double.parseDouble(lanlng[1]))));
//
                                    CameraUpdate move = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lanlng[0]),
                                            Double.parseDouble(lanlng[1])), 15f);
                                    mMap.animateCamera(move);

                                    binding.endsub.setText("");
                                    binding.startsub.setText("");

                                    startflag = false;
                                    endflag = false;

                                }

                                @Override
                                public void onFailure(Call<DirectionResponses> call, Throwable t) {
                                    Toast.makeText(LoadActivity.this, "실패", Toast.LENGTH_LONG).show();

                                }
                            });

                }else{
                    Toast.makeText(LoadActivity.this,"출발지와 도착지를 모두 선택해주세요.",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private void drawPolyline(@NonNull Response<DirectionResponses> response)
    {
        if(response.body() != null){

            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            PolylineOptions polyline = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(30f)
                    .color(Color.RED);

            mMap.addPolyline(polyline);

        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;

        LatLng SEOUL = new LatLng(37.56,126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("수도");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL,15));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();

        }
        return true;
    }
}

