package cumunus.orderdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    RelativeLayout cart;
    TextView txtcount;
    Button btncheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);


        CartUtils.clearCart();

        btncheckout = (Button) findViewById(R.id.btnadd);

        btncheckout.setVisibility(View.GONE);

        setupProductsList();

        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) topToolBar.findViewById(R.id.title);
        cart = (RelativeLayout) topToolBar.findViewById(R.id.cart);
        txtcount = (TextView) topToolBar.findViewById(R.id.count);
        ImageView logo = (ImageView) topToolBar.findViewById(R.id.fulllogo);
        ImageView p = (ImageView) topToolBar.findViewById(R.id.p);

        p.setVisibility(View.VISIBLE);


        setTitle("");
        setSupportActionBar(topToolBar);


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setupProductsList() {

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Product>>() {
        }.getType();
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());

            List<Product> myModelList = gson.fromJson(jsonObject.getJSONArray("products").toString(), listType);

            listView.setAdapter(new ListAdapter(this, myModelList));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public void changeCartIcon() {
        if (CartUtils.getCart().size() > 0) {
            cart.setVisibility(View.VISIBLE);
            btncheckout.setVisibility(View.VISIBLE);
        } else {
            cart.setVisibility(View.INVISIBLE);
            btncheckout.setVisibility(View.GONE);

        }

        int count = 0;

        for (Product p : CartUtils.getCart()) {
            count = count + p.getSelected_qty();
        }

        txtcount.setText(count + "");

    }


}
