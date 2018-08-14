package com.suemi_13_15.recordatorios.view;


import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



import com.suemi_13_15.recordatorios.R;
import com.suemi_13_15.recordatorios.view.reminder.RemindersFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se obtiene toolbar
        toolbar = findViewById(R.id.toolbar);
        //Coloca la toolbar manualmente, para que funcione se necesita utilizar un tema sin toolbar
        setSupportActionBar(toolbar);

        //Se obtiene actionBar
        actionBar = getSupportActionBar();
        //Se le cambia el icono por defecto que es home por el recurso deseado
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //Mostrar el icono
        actionBar.setDisplayHomeAsUpEnabled(true);


        //se obtiene el drawerLayout
        drawerLayout = findViewById(R.id.navigation_drawer_layout);


        //se crea variable tipo NavigationView y se le asigna la obtencion de NavigationView mediante el id
        NavigationView navigationView = findViewById(R.id.navigation_view);

        //se comprueba que si el navigationView es diferente de null se ejecuta
        // el metodo que manda el listener a los items
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        //First fragment
        setFragment(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //infla el menu, esto agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.navigation_drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Si se selcciona el boton home se abre el navigationDrawer
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener
                (new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            //Si se da click en el item en cuestion
                            case R.id.item_navigation_drawer_reminder:
                                //hace que el item seleccionado se quede sombreado
                                menuItem.setChecked(true);
                                //y se ejecuta el metodo setFragment mediante el parametro de la posicion
                                setFragment(0);
                                //se cierra el navigationDrawer
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }

    //Metodo para hacer la transaccion de fragments
    public void setFragment(int position) {
        //Se necesita un FragmentManager y un FragmentTransaction para hacer esa transaccion de fragments
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                //se obtiene el fragmentManager
                fragmentManager = getSupportFragmentManager();
                //mediante ese fragmente se accede al metodo para hacer la transaccion
                fragmentTransaction = fragmentManager.beginTransaction();
                //Se crea una variable del tipo del fragment que se quiere transferir y se hace una instancia del mismo
                RemindersFragment remindersFragment = new RemindersFragment();
                //mediante la transaccion se le dice en donde va a reemplazar el fragmente que queremos
                fragmentTransaction.replace(R.id.fragment, remindersFragment);
                //y se realiza la transaccion
                fragmentTransaction.commit();
                break;

        }
    }
}