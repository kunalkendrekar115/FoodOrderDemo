package cumunus.orderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CheckoutActivity extends AppCompatActivity {

    LinearLayout container;
    LinearLayout btnPay;
    TextView txtTotal;
    private RelativeLayout cart;
    Button btnAdd;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        btnPay = (LinearLayout) findViewById(R.id.btnPay);
        txtTotal = (TextView) findViewById(R.id.total);
        cart=(RelativeLayout)toolbar.findViewById(R.id.cart);
        TextView txtcount=(TextView)toolbar.findViewById(R.id.count);


        for (Product p : CartUtils.getCart()) {
            total += (p.getPrice() * p.getSelected_qty());
        }


        if(CartUtils.getCart().size()>0)
        {
            cart.setVisibility(View.VISIBLE);
        }
        else
        {
            cart.setVisibility(View.INVISIBLE);

        }

        int count=0;

        for(Product p:CartUtils.getCart())
        {
            count=count+p.getSelected_qty();
        }

        txtcount.setText(count+"");

        txtTotal.setText(getString(R.string.rupee) + " " + total);
        container = (LinearLayout) findViewById(R.id.container);

        for (Product p : CartUtils.getCart()) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.cart_row, null);
            ImageView img = (ImageView) v.findViewById(R.id.img);

            switch (p.getId()) {
                case 1:

                    img.setImageResource(R.drawable.sandwich);
                    break;

                case 2:

                    img.setImageResource(R.drawable.wrap);
                    break;

                case 3:

                    img.setImageResource(R.drawable.salad);
                    break;

                case 4:

                    img.setImageResource(R.drawable.biryani);
                    break;
            }
            TextView name = (TextView) v.findViewById(R.id.name);
            TextView price = (TextView) v.findViewById(R.id.price);
            name.setText(p.getSelected_qty() + " - " + p.getProductname());
            int pr = p.getPrice() * p.getSelected_qty();
            price.setText(getString(R.string.rupee) + " " + pr);

            container.addView(v);

            btnAdd= (Button) findViewById(R.id.btnadd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(CheckoutActivity.this,PaymentOptions.class);
                    startActivity(intent);
                }
            });

        }


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),OrderPlaced.class);
                intent.putExtra("amount",total+"");
                intent.putExtra("txnid","12345");

                startActivity(intent);

            }
        });

    }




}
