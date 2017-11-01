package com.example.navigationmenu;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    //Muutujate lisamine:
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    //Override meetod:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Seab activity-le layout-i
        mToolbar = (Toolbar) findViewById(R.id.nav_action); //Leiab Toolbar-i nimega 'nav_action'
        setSupportActionBar(mToolbar); //Näitab activity nime
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);//Leiab DrawerLayout-i nimega 'drawerLayout'
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);//Lisab ActionBar-ile "Open", "Close" olekud
        mDrawerLayout.addDrawerListener(mToggle);//Drawer-i tegevusi
        mToggle.syncState(); //Sünkroniseerib mToggle muutuja
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();//Fragment-i tegevuse algataja

        fragmentTransaction.add(R.id.main_container, new HomeFragment());//Vahetab Fragment-i 'HomeFragmenti'-i peale
        fragmentTransaction.commit();//Ühendab Fragment-id
        getSupportActionBar().setTitle("Home fragment...");//Muudab Fragment-i pealkirja

        navigationView = (NavigationView)findViewById(R.id.navigation_menu);//Leiab NavigationView nimega 'navigation_menu'
        //Uus meetod:
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Vahetab 'Item'-i id
                switch (item.getItemId()) {
                    //"case..." käivitab vastava Fragmenti olenevalt valikust
                    case R.id.nav_home_fragment:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();//Fragment-i tegevuse algataja
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());//Vahetab Fragment-i vastava Fragment-i peale
                        fragmentTransaction.commit();//Ühendab Fragment-id
                        getSupportActionBar().setTitle("Home fragment");//Muudab Fragment-i pealkirja
                        item.setChecked(true);//Seab Item-i "true" peale
                        mDrawerLayout.closeDrawers(); //Sulgeb Drawer-id
                        break;//Sulgeb Fragment-i
                    case R.id.nav_email_fragment:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new EmailFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Email fragment");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_calendar_fragment:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new CalendarFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Calendar fragment");
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true; //Tulemuse väljastus
            }
            });
        }
    //Uus meetod:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) { //Kontrollib kas 'Item' on valitud
            return true;//Positiivse vastuse korral väljastab 'true'
        }
        return super.onOptionsItemSelected(item);//Väljastus negatiivse vastuse korral
    }
}