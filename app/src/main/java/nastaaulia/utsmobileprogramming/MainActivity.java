package nastaaulia.utsmobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nastaaulia.utsmobileprogramming.utils.ProductListAdapter;
import nastaaulia.utsmobileprogramming.utils.SessionManagement;

public class MainActivity extends AppCompatActivity {

    SessionManagement session;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;

    public static int[] image = {
            R.drawable.cupcake_oreo,
            R.drawable.cupcake_coklat,
            R.drawable.cupcake_tiramisu,
            R.drawable.cupcake_greentea,
            R.drawable.cupcake_redvelvet,
            R.drawable.cupcake_taro
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        setProduct();

        productListView = (ListView) findViewById(R.id.list_product);
        productListAdapter = new ProductListAdapter(getApplicationContext(), products, image);
        productListView.setAdapter(productListAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Product product = (Product)adapterView.getItemAtPosition(position);
                String price = String.valueOf(product.getPrice());

                Intent iDetail = new Intent(getApplicationContext(), Pemesanan.class);
                iDetail.putExtra("position", position);
                iDetail.putExtra("nama",product.getName());
                startActivity(iDetail);
            }
        });
    }

    private void setProduct(){

        Product product1 = new Product(1,"Cupcake Oreo","Kue dengan rasa oreo",6500);
        Product product2 = new Product(2,"Cupcake Coklat","Kue dengan rasa coklat",6000);
        Product product3 = new Product(3,"Cupcake Tiramisu","Kue dengan rasa tiramisu",7000);
        Product product4 = new Product(4,"Cupcake Greentea","Kue dengan aroma green tea",8000);
        Product product5 = new Product(5,"Cupcake Red Velvet","Kue dengan rasa red velvet",7500);
        Product product6 = new Product(5,"Cupcake Taro","Kue dengan aroma taro",7500);


        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                session.logoutUser();

                break;
        }
        return true;
    }
}
