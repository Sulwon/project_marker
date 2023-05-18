package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    String shared = "file";
    //변수 선언
    private ImageView say;
    private ImageView reedit;
    private ImageView markoff;
    private ImageView markon;
    private TextView tv;
    private MapView mapView;
    private EditText et;
    private static NaverMap naverMap;
    private static final int ACCESS_LOCATION_PERMISSION_REQUEST_CODE = 100;
    private FusedLocationSource locationSource;
    private int selecting;
    //마커 변수 선언 및 초기화
    private Marker marker1 = new Marker();     //수덕전
    private Marker marker2 = new Marker();     //자연과학관
    private Marker marker3 = new Marker();     //정보관
    private Marker marker4 = new Marker();     //공과대학
    private Marker marker5 = new Marker();     //제 1,2 인문관
    private Marker marker6 = new Marker();     //상영관
    private Marker marker7 = new Marker();     //상경관
    private Marker marker8 = new Marker();     //법정관
    private Marker marker9 = new Marker();     //국제관
    private Marker marker10 = new Marker();     //생활과학관
    private Marker marker11 = new Marker();     //효민생활관
    private Marker marker12 = new Marker();     //제2 효민생활관
    private Marker marker13 = new Marker();     //행복기숙사
    private String description;
    /* 수덕전 */ private String description1;
    /* 자연과학관 */ private String description2;
    /* 정보관 */ private String description3;
    /* 공과대학 */ private String description4;
    /* 제 1,2 인문관 */ private String description5;
    /* 상영관 */ private String description6;
    /* 상경관 */ private String description7;
    /* 법정관 */ private String description8;
    /* 국제관 */ private String description9;
    /* 생활과학관 */ private String description10;
    /* 효민생활관 */ private String description11;
    /* 제2 효민생활관 */ private String description12;
    /* 행복기숙사 */ private String description13;
    private InfoWindow infoWindow = new InfoWindow();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        description1 = sharedPreferences.getString("data1","휴식공간과 복지공간으로 이루어진 학생전용공간");
        description2 = sharedPreferences.getString("data2","자연대교차로 쪽에 부 출입구 있어요.");
        description3 = sharedPreferences.getString("data3","2층에 GS25가 있어요.");
        description4 = sharedPreferences.getString("data4", "자연과학관 쪽 계단을 통해서 \n중앙도서관 쪽의 급경사로\n접근할 수 있어요.");
        description5 = sharedPreferences.getString("data5","중앙도서관과 수덕전 사이\n나선 경사로를 통해서도 갈 수 있습니다.");
        description6 = sharedPreferences.getString("data6","2층에 서점이 있어요.");
        description7 = sharedPreferences.getString("data7","상경대학/예술·체육대학");
        description8 = sharedPreferences.getString("data8","마을버스 회차점이에요.");
        description9 = sharedPreferences.getString("data9","주요시설 : 경영대학원, 석당아트홀, 효민갤러리");
        description10 = sharedPreferences.getString("data10","자연·생활과학대학/예술·체육대학");
        description11 = sharedPreferences.getString("data11","미래형 첨단 기숙사입니다!");
        description12 = sharedPreferences.getString("data12","2008년 착공하여 2010년 개관했어요.");
        description13 = sharedPreferences.getString("data13","2013년 교육부와 국토교통부가 공동으로 건립됨.\n" +
                "주요시설 : 휴게실, 정독실, 세미나실\n" +
                "통금시간 00:00 - 05:00");

        say = findViewById(R.id.say);
        tv = findViewById(R.id.tv);
        reedit = findViewById(R.id.reedit);
        et = findViewById(R.id.et);
        markoff = findViewById(R.id.markon);
        markon = findViewById(R.id.markoff);

        reedit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tv.getVisibility() == View.VISIBLE) {
                    et.setText(tv.getText());
                    tv.setVisibility((View.INVISIBLE));
                    et.setVisibility((View.VISIBLE));
                    Toast.makeText(getApplicationContext(),"수정할 내용을 입력해주세요.",Toast.LENGTH_SHORT).show();
                } else {
                    tv.setText(et.getText());
                    tv.setVisibility((View.VISIBLE));
                    et.setVisibility((View.INVISIBLE));
                    switch(selecting) {
                        case 1:
                            description1 = et.getText().toString();
                            break;
                        case 2:
                            description2 = et.getText().toString();
                            break;
                        case 3:
                            description3 = et.getText().toString();
                            break;
                        case 4:
                            description4 = et.getText().toString();
                            break;
                        case 5:
                            description5 = et.getText().toString();
                            break;
                        case 6:
                            description6 = et.getText().toString();
                            break;
                        case 7:
                            description7 = et.getText().toString();
                            break;
                        case 8:
                            description8 = et.getText().toString();
                            break;
                        case 9:
                            description9 = et.getText().toString();
                            break;
                        case 10:
                            description10 = et.getText().toString();
                            break;
                        case 11:
                            description11 = et.getText().toString();
                            break;
                        case 12:
                            description12 = et.getText().toString();
                            break;
                        case 13:
                            description13 = et.getText().toString();
                            break;
                    }
                    Toast.makeText(getApplicationContext(),"성공적으로 수정되었습니다.",Toast.LENGTH_SHORT).show();
                }

            }
        }));

        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getVisibility() == View.INVISIBLE) {
                    reedit.setVisibility((View.INVISIBLE));
                    say.setVisibility((View.INVISIBLE));
                    tv.setVisibility((View.INVISIBLE));
                }
            }
        });
        marker1.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 1;
                    description = description1;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker2.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 2;
                    description = description2;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker3.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 3;
                    description = description3;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker4.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 4;
                    description = description4;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker5.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 5;
                    description = description5;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker6.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 6;
                    description = description6;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker7.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 7;
                    description = description7;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker8.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 8;
                    description = description8;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker9.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 9;
                    description = description9;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker10.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 10;
                    description = description10;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker11.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 11;
                    description = description11;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker12.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 12;
                    description = description12;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        marker13.setOnClickListener(new Overlay.OnClickListener()
        {
            @Override
            public boolean onClick(@NonNull Overlay overlay)
            {
                if(et.getVisibility() == View.INVISIBLE) {
                    selecting = 13;
                    description = description13;
                    tv.setText(description);
                    say.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    reedit.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        markon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markon.setVisibility(View.INVISIBLE);
                markoff.setVisibility(View.VISIBLE);
                marker1.setVisible(false);
                marker2.setVisible(false);
                marker3.setVisible(false);
                marker4.setVisible(false);
                marker5.setVisible(false);
                marker6.setVisible(false);
                marker7.setVisible(false);
                marker8.setVisible(false);
                marker9.setVisible(false);
                marker10.setVisible(false);
                marker11.setVisible(false);
                marker12.setVisible(false);
                marker13.setVisible(false);
                say.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.INVISIBLE);
                reedit.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"마크를 비활성화 하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        markoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markon.setVisibility(View.VISIBLE);
                markoff.setVisibility(View.INVISIBLE);
                marker1.setVisible(true);
                marker2.setVisible(true);
                marker3.setVisible(true);
                marker4.setVisible(true);
                marker5.setVisible(true);
                marker6.setVisible(true);
                marker7.setVisible(true);
                marker8.setVisible(true);
                marker9.setVisible(true);
                marker10.setVisible(true);
                marker11.setVisible(true);
                marker12.setVisible(true);
                marker13.setVisible(true);
                say.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.INVISIBLE);
                reedit.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"마크를 활성화 하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        //네이버 지도
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap)
    {
        this.naverMap = naverMap;


        //배경 지도 선택
        naverMap.setMapType(NaverMap.MapType.Basic);
        //건물 표시
        naverMap.setLayerGroupEnabled(naverMap.LAYER_GROUP_BUILDING, true);
        //위치 및 각도 조정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(35.142963, 129.034097),   // 위치 지정
                16,                           // 줌 레벨
                0,                          // 기울임 각도
                0                           // 방향
        );
        naverMap.setCameraPosition(cameraPosition);

        locationSource = new FusedLocationSource(this, ACCESS_LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.setLocationSource(locationSource);
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);
        setMarker(marker1, 35.1414389, 129.0346019, R.drawable.ic_baseline_place_24, 0); // 수덕전
        setMarker(marker2, 35.1436278, 129.0351598, R.drawable.ic_baseline_place_24, 0); // 자연과학관
        setMarker(marker3, 35.1459658, 129.0366887, R.drawable.ic_baseline_place_24, 0); // 정보관
        setMarker(marker4, 35.14437, 129.03635, R.drawable.ic_baseline_place_24, 0); // 공과대학
        setMarker(marker5, 35.14142, 129.03542, R.drawable.ic_baseline_place_24, 0); // 제1,2 인문관
        setMarker(marker6, 35.14087, 129.03429, R.drawable.ic_baseline_place_24, 0); // 상영관
        setMarker(marker7, 35.13964, 129.03275, R.drawable.ic_baseline_place_24, 0); // 상경대학
        setMarker(marker8, 35.13952, 129.03422, R.drawable.ic_baseline_place_24, 0); // 법정대학
        setMarker(marker9, 35.14070, 129.03241, R.drawable.ic_baseline_place_24, 0); // 국제관
        setMarker(marker10, 35.14356, 129.03359, R.drawable.ic_baseline_place_24, 0); // 생활과학관
        setMarker(marker11, 35.14383, 129.03812, R.drawable.ic_baseline_place_24, 0); // 효민생활관
        setMarker(marker12, 35.14305, 129.03280, R.drawable.ic_baseline_place_24, 0); // 제2 효민생활관
        setMarker(marker13, 35.14174, 129.03357, R.drawable.ic_baseline_place_24, 0); // 행복기숙사
    }

    private void setMarker(Marker marker,  double lat, double lng, int resourceID, int zIndex)
    {
        //원근감 표시
        marker.setIconPerspectiveEnabled(true);
        //아이콘 지정
        marker.setIcon(OverlayImage.fromResource(resourceID));
        //마커의 투명도
        marker.setAlpha(0.8f);
        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        //마커 우선순위
        marker.setZIndex(zIndex);
        //마커 표시
        marker.setMap(naverMap);
    }

    private void setInfoWindow(InfoWindow infoWindow,  double lat, double lng, int resourceID, int zIndex)
    {
    }
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        switch (requestCode) {
            case ACCESS_LOCATION_PERMISSION_REQUEST_CODE:
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }

    }

    @Override
    public void onStart()
    {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data1",description1);
        editor.putString("data2",description2);
        editor.putString("data3",description3);
        editor.putString("data4",description4);
        editor.putString("data5",description5);
        editor.putString("data6",description6);
        editor.putString("data7",description7);
        editor.putString("data8",description8);
        editor.putString("data9",description9);
        editor.putString("data10",description10);
        editor.putString("data11",description11);
        editor.putString("data12",description12);
        editor.putString("data13",description13);
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
