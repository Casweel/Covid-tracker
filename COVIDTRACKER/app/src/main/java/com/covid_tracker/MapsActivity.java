package com.covid_tracker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ProgressBar progressBar;
    LatLng here = new LatLng(55, 37);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setIcon(R.drawable.corona);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));

        FrameLayout frameLayout = findViewById(R.id.map);
        View view2 = getLayoutInflater().inflate(R.layout.progress, frameLayout, false);
        frameLayout.addView(view2);
        progressBar = frameLayout.findViewById(R.id.progressbar);

        if (isOnline(this)) {
            Update update = new Update(this, mMap, progressBar);
            update.execute();
        }
        else
            Toast.makeText(getApplicationContext(),
                    "Для загрузки данных необходим интернет. Чтобы загрузить данные позднее, нажмите кнопку обновить.", Toast.LENGTH_LONG).show();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag = super.onOptionsItemSelected(item);
        if (isOnline(this)) {
            mMap.clear();
            Update update = new Update(this, mMap, progressBar);
            update.execute();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Для загрузки данных необходим интернет. Чтобы загрузить данные позднее, нажмите кнопку обновить.", Toast.LENGTH_LONG).show();
        }
        return flag;
    }

    public static String rusCountryName(String country) {
        switch (country) {
            case "Afghanistan":
                return "Афганистан";
            case "Albania":
                return "Албания";
            case "Algeria":
                return "Алжир";
            case "Andorra":
                return "Андорра";
            case "Angola":
                return "Ангола";
            case "Antigua and Barbuda":
                return "Антигуа и Барбуда";
            case "Argentina":
                return "Аргентина";
            case "Armenia":
                return "Армения";
            case "Australia":
                return "Австралия";
            case "Austria":
                return "Австрия";
            case "Azerbaijan":
                return "Азербайджан";
            case "Bahamas":
                return "Багамы";
            case "Bahrain":
                return "Бахрейн";
            case "Bangladesh":
                return "Бангладеш";
            case "Barbados":
                return "Барбадос";
            case "Belarus":
                return "Беларусь";
            case "Belgium":
                return "Бельгия";
            case "Benin":
                return "Бенин";
            case "Bhutan":
                return "Бутан";
            case "Bolivia":
                return "Боливия";
            case "Bosnia and Herzegovina":
                return "Босния и Герцоговина";
            case "Brazil":
                return "Бразилия";
            case "Brunei":
                return "Бруней";
            case "Bulgaria":
                return "Болгария";
            case "Burkina Faso":
                return "Буркина-Фасо";
            case "Cabo Verde":
                return "Кабо-Верде";
            case "Cambodia":
                return "Камбоджа";
            case "Cameroon":
                return "Камерун";
            case "Canada":
                return "Канада";
            case "Central African Republic":
                return "Центральноафриканская Республика";
            case "Chad":
                return "Чад";
            case "Chile":
                return "Чили";
            case "China":
                return "Китай";
            case "Colombia":
                return "Колумбия";
            case "Congo (Brazzaville)":
                return "Конго (Браззавиль)";
            case "Congo (Kinshasa)":
                return "Конго (Киншаса)";
            case "Costa Rica":
                return "Коста-Рика";
            case "Cote d'Ivoire":
                return "Кот-д'Ивуар";
            case "Croatia":
                return "Хорватия";
            case "Diamond Princess":
                return "судно Бриллиантовая принцесса";
            case "Cuba":
                return "Куба";
            case "Cyprus":
                return "Кипр";
            case "Czechia":
                return "Чехия";
            case "Denmark":
                return "Дания";
            case "Djibouti":
                return "Джибути";
            case "Dominican Republic":
                return "Доминиканская Республика";
            case "Ecuador":
                return "Эквадор";
            case "Egypt":
                return "Египт";
            case "El Salvador":
                return "Сальвадор";
            case "Equatorial Guinea":
                return "Экваториальная Гвинея";
            case "Eritrea":
                return "Эритрея";
            case "Estonia":
                return "Эстония";
            case "Eswatini":
                return "Эсватини";
            case "Ethiopia":
                return "Эфиопия";
            case "Fiji":
                return "Фиджи";
            case "Finland":
                return "Финляндия";
            case "France":
                return "Франция";
            case "Gabon":
                return "Габон";
            case "Gambia":
                return "Гамбия";
            case "Georgia":
                return "Грузия";
            case "Germany":
                return "Германия";
            case "Ghana":
                return "Гана";
            case "Greece":
                return "Греция";
            case "Guatemala":
                return "Гватемала";
            case "Guinea":
                return "Гвинея";
            case "Guyana":
                return "Гайана";
            case "Haiti":
                return "Гаити";
            case "Holy See":
                return "Святой Престол";
            case "Honduras":
                return "Гондурас";
            case "Hungary":
                return "Венгрия";
            case "Iceland":
                return "Исландия";
            case "India":
                return "Индия";
            case "Indonesia":
                return "Индонезия";
            case "Iran":
                return "Иран";
            case "Iraq":
                return "Ирак";
            case "Ireland":
                return "Ирландия ";
            case "Israel":
                return "Израиль";
            case "Italy":
                return "Италия";
            case "Jamaica":
                return "Ямайка";
            case "Japan":
                return "Япония";
            case "Jordan":
                return "Иордания";
            case "Kazakhstan":
                return "Казахстан";
            case "Kenya":
                return "Кения";
            case "Korea, South":
                return "Южная Корея";
            case "Kuwait":
                return "Кувейт";
            case "Kyrgyzstan":
                return "Киргизстан";
            case "Latvia":
                return "Латвия";
            case "Lebanon":
                return "Ливан";
            case "Liberia":
                return "Либерия";
            case "Liechtenstein":
                return "Лихтенштейн";
            case "Lithuania":
                return "Литва";
            case "Luxembourg":
                return "Люксембург";
            case "Madagascar":
                return "Мадагаскар";
            case "Malaysia":
                return "Малазия";
            case "Maldives":
                return "Мальдивы";
            case "Malta":
                return "Мальта";
            case "Mauritania":
                return "Мавритания";
            case "Mauritius":
                return "о. Маврикий";
            case "Mexico":
                return "Мексика";
            case "Moldova":
                return "Молдавия";
            case "Monaco":
                return "Монако";
            case "Mongolia":
                return "Монголия";
            case "Montenegro":
                return "Черногория";
            case "Morocco":
                return "Марокко";
            case "Namibia":
                return "Намибия";
            case "Nepal":
                return "Непал";
            case "Netherlands":
                return "Нидерланды";
            case "New Zealand":
                return "Новая Зеландия";
            case "Nicaragua":
                return "Никарагуа";
            case "Niger":
                return "Нигер";
            case "Nigeria":
                return "Нигерия";
            case "North Macedonia":
                return "Македония ";
            case "Norway":
                return "Норвегия ";
            case "Oman":
                return "Оман";
            case "Pakistan":
                return "Пакистан";
            case "Panama":
                return "Панама";
            case "Papua New Guinea":
                return "Папуа-Новая Гвинея";
            case "Paraguay":
                return "Парагвай";
            case "Peru":
                return "Перу";
            case "Philippines":
                return "Филлипины";
            case "Poland":
                return "Польша";
            case "Portugal":
                return "Португалия";
            case "Qatar":
                return "Катар";
            case "Romania":
                return "Румыния";
            case "Russia":
                return "Россия";
            case "Rwanda":
                return "Руанда";
            case "Saint Lucia":
                return "Сент-Люсия";
            case "Saint Vincent and the Grenadines":
                return "Сент-Винсент и Гренадины";
            case "San Marino":
                return "Сан-Марино";
            case "Saudi Arabia":
                return "Саудовская Аравия";
            case "Senegal":
                return "Сенегал";
            case "Serbia":
                return "Сербия";
            case "Seychelles":
                return "Сейшелы";
            case "Singapore":
                return "Сингапур";
            case "Slovakia":
                return "Словакия";
            case "Slovenia":
                return "Словения";
            case "Somalia":
                return "Сомали";
            case "South Africa":
                return "Южная Африка";
            case "Spain":
                return "Испания";
            case "Sri Lanka":
                return "Шри-Ланка";
            case "Sudan":
                return "Судан";
            case "Suriname":
                return "Суринам";
            case "Sweden":
                return "Швеция";
            case "Switzerland":
                return "Швейцария";
            case "Taiwan*":
                return "Тайвань";
            case "Tanzania":
                return "Танзания";
            case "Thailand":
                return "Тайланд";
            case "Togo":
                return "Того";
            case "Trinidad and Tobago":
                return "Тринидад и Тобаго";
            case "Tunisia":
                return "Тунис";
            case "Turkey":
                return "Турция";
            case "Uganda":
                return "Уганда";
            case "Ukraine":
                return "Украина";
            case "United Arab Emirates":
                return "Объединенные Арабские Эмираты";
            case "United Kingdom":
                return "Великобритания";
            case "Uruguay":
                return "Уругвай";
            case "US":
                return "Соединенные штаты";
            case "Uzbekistan":
                return "Узбекистан";
            case "Venezuela":
                return "Венесуэла";
            case "Vietnam":
                return "Вьетнам";
            case "Zambia":
                return "Замбия";
            case "Zimbabwe":
                return "Зимбабве";
            case "Dominica":
                return "Доминика";
            case "Grenada":
                return "Гренада";
            case "Mozambique":
                return "Мозамбик";
            case "Syria":
                return "Сирия";
            case "Timor-Leste":
                return "Восточный Тимор";
            case "Belize":
                return "Белиз";
            case "Laos":
                return "Лаос";
            case "Libya":
                return "Ливия";
            case "West Bank and Gaza":
                return "Палестинские территории";
            case "Guinea-Bissau":
                return "Гвинея-Бисау";
            case "Mali":
                return "Мали";
            case "Saint Kitts and Nevis":
                return "Сент-Китс и Невис";
            case "Kosovo":
                return "Косово";
            case "Burma":
                return "Бирма";
            case "MS Zaandam":
                return "судно MS Zaandam";
            case "Botswana":
                return "Ботсвана";
            case "Burundi":
                return "Бурунди";
            case "Sierra Leone":
                return "Сьерра-Леоне";
            case "Malawi":
                return "Малави";
            case "South Sudan":
                return "Южный Судан";
            case "Western Sahara":
                return "Западная Сахара";
            case "Sao Tome and Principe":
                return "Сан-Томе и Принсипи";
            case "Yemen":
                return "Йемен";
            case "Comoros":
                return "Коморы";
            case "Tajikistan":
                return "Таджикистан";
            default:
                return "Не найдено";
        }
    }

    public class Update extends AsyncTask<Void, com.covid_tracker.ToReturn, Boolean> {
        private GoogleMap mMap;
        private Context context;
        private ProgressBar progressBar;

        Update(Context context, GoogleMap mMap, ProgressBar progressBar) {
            this.context = context;
            this.mMap = mMap;
            this.progressBar = progressBar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Boolean doInBackground(Void... data) {
            com.covid_tracker.Coronavirus coronavirus = new com.covid_tracker.Coronavirus();
            com.covid_tracker.ToReturn result = null;
            try {
                result = new com.covid_tracker.ToReturn(coronavirus.getLatest(), coronavirus.getLocations(), context, mMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            com.covid_tracker.Latest latest = result.getLatest();
            ArrayList<com.covid_tracker.Location> location = result.getLocation();
            publishProgress(result);
            return true;
        }

        @Override
        protected void onProgressUpdate(com.covid_tracker.ToReturn... result) {
            ArrayList<com.covid_tracker.Location> location = result[0].getLocation();
            mMap.clear();

            ClusterManager<com.covid_tracker.MyItem> mClusterManager;
            mClusterManager = new ClusterManager<>(context, mMap);
            mMap.setOnCameraIdleListener(mClusterManager);

            for (com.covid_tracker.Location obj : location
            ) {
                double lat = obj.getCoordinates().getLatitude();
                double lng = obj.getCoordinates().getLongitude();

                if (obj.getId() == 187) {
                    lat = 55.753595;
                    lng = 37.621031;
                }

                IconGenerator iconFactory = new IconGenerator(context);
                iconFactory.setBackground(context.getResources().getDrawable(R.drawable.shape));
                iconFactory.setTextAppearance(R.style.itemText);

                com.covid_tracker.MyItem myItem = new com.covid_tracker.MyItem(lat, lng);
                myItem.setId(obj.getId());
                myItem.setConfirmed(obj.getLatest().getConfirmed());
                myItem.setMarker(new MarkerOptions().
                        icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(String.valueOf(obj.getLatest().getConfirmed())))).
                        title("Подтверждено случаев: " + myItem.getConfirmed() + "\n" +
                                "Смертей: " + obj.getLatest().getDeaths()).
                        position(new LatLng(lat, lng)));

                mClusterManager.setRenderer(new com.covid_tracker.OwnInformation(
                        context.getApplicationContext(), mMap, mClusterManager));
                mClusterManager.addItem(myItem);
            }

            mMap.setOnMarkerClickListener(mClusterManager);

            mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<com.covid_tracker.MyItem>() {
                @Override
                public boolean onClusterItemClick(com.covid_tracker.MyItem item) {
                    if (item != null) {
                        int deaths = 0;
                        int confirmed = 0;
                        String country = "";
                        for (com.covid_tracker.Location obj : location
                        ) {
                            if (item.getId() == obj.getId()) {
                                country = MapsActivity.rusCountryName(obj.getCountry());
                                deaths = obj.getLatest().getDeaths();
                                confirmed = obj.getLatest().getConfirmed();
                            }
                        }
                        Toast.makeText(context.getApplicationContext(),
                                country + "\n" +
                                        "Заболевших: " + confirmed + "\n" +
                                        "Смертей: " + deaths, Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

            });

            mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<com.covid_tracker.MyItem>() {
                @Override
                public boolean onClusterClick(Cluster<com.covid_tracker.MyItem> cluster) {
                    if (cluster != null) {
                        int deaths = 0;
                        int confirmed = 0;
                        String string = "Страна: заболевшие: смерти";

                        for (com.covid_tracker.MyItem myItem : cluster.getItems()) {
                            for (com.covid_tracker.Location obj : location
                            ) {
                                if (myItem.getId() == obj.getId()) {
                                    deaths = obj.getLatest().getDeaths();
                                    confirmed = obj.getLatest().getConfirmed();
                                    string += "\n" + MapsActivity.rusCountryName(obj.getCountry()) + " : " + confirmed + " " +
                                            " : " + deaths;
                                }
                            }
                        }
                        Toast.makeText(context.getApplicationContext(),
                                string, Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });


        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}

