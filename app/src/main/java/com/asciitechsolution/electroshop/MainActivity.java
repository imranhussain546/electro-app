package com.asciitechsolution.electroshop;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;

import com.asciitechsolution.electroshop.api.ApiClient;
import com.asciitechsolution.electroshop.ui.brand.BrandsFragment;
import com.asciitechsolution.electroshop.ui.category.CategoryFragment;
import com.asciitechsolution.electroshop.ui.offers.OfferFragment;
import com.asciitechsolution.electroshop.ui.service.ServiceFragment;
import com.asciitechsolution.electroshop.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    public static BottomNavigationView bottomNavigationView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        setSupportActionBar(toolbar);
        databaseHelper = new DatabaseHelper(this);
         drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.nav_header_desc,R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_service, R.id.nav_brand)
                .setDrawerLayout(drawer)
                .build();

        Constant.TOTAL_CART_ITEM=databaseHelper.getTotalItemOfCart(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        loadFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_offer:
                        loadFragment(new OfferFragment());
                        return true;

                    case R.id.navigation_service:
                        loadFragment(new ServiceFragment());
                        return true;

                    case R.id.navigation_brand:
                        loadFragment(new BrandsFragment());
                        return true;
                    case R.id.navigation_category:
                        loadFragment(new CategoryFragment());
                        return true;
                }
                return false;
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_search_icon:
                loadFragment(new SearchFragment());
                break;
            case R.id.main_heart_icon:
                loadFragment(new FavoriteFragment());
                break;
            case R.id.main_cart_icon:
                loadFragment(new CartFragment());
                break;
            case R.id.main_profile_icon:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
     switch (item.getItemId())
        {
            case R.id.nav_home:
                loadFragment(new HomeFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_service:
                loadFragment(new ServiceFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_brand:
                loadFragment(new BrandsFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_pofile:
                loadFragment(new UserProfileFragment());
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.main_search_icon).setVisible(false);
        menu.findItem(R.id.main_cart_icon).setVisible(true);
        menu.findItem(R.id.main_heart_icon).setVisible(true);
        menu.findItem(R.id.main_profile_icon).setVisible(true);
        menu.findItem(R.id.main_cart_icon).setIcon(ApiClient.buildCounterDrawable(Constant.TOTAL_CART_ITEM,R.drawable.ic_cart,this));
        this.invalidateOptionsMenu();
       // Log.d("MainActivity", "onPrepareOptionsMenu: "+Constant.TOTAL_CART_ITEM);
        return super.onPrepareOptionsMenu(menu);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.commit();
    }
}