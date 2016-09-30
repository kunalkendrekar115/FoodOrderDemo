package cumunus.orderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderPlaced extends AppCompatActivity {

    private Button btnDone;
    LinearLayout container;
    TextView txtTotal,paysoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);


        btnDone = (Button) findViewById(R.id.btndone);
        txtTotal = (TextView) findViewById(R.id.total);
        paysoID = (TextView) findViewById(R.id.paysoID);


        paysoID.setText(getIntent().getStringExtra("txnid"));

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               redirectToMain();
            }
        });

        int total = 0;
        for (Product p : CartUtils.getCart()) {
            total += (p.getPrice() * p.getSelected_qty());
        }

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


        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        redirectToMain();
    }

    private void redirectToMain() {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
