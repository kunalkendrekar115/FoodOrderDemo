package cumunus.orderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class PaymentOptions extends AppCompatActivity {

    private Spinner sp1,sp2,sp3;
    private String[] creditcard={"Visa","Master Card"};
    private String[] netbanking={"ICICI","Axis Kotak","Yes","City","SBI"};
    private String[] debitcard={"ICICI","Axis Kotak","Yes","City","SBI"};
    Button btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        setTitle(null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.title);
        //title.setText("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        RelativeLayout cart=(RelativeLayout)toolbar.findViewById(R.id.cart);
        TextView txtcount=(TextView)toolbar.findViewById(R.id.count);

        int total = 0;
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

        txtcount.setText(count + "");

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
                finish();
            }
        });

        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        sp3 = (Spinner) findViewById(R.id.sp3);

        btncancel= (Button) findViewById(R.id.btnCancel);

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter<String> a1 = new ArrayAdapter<String>(this, R.layout.text, creditcard);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(a1);


        ArrayAdapter<String> a2 = new ArrayAdapter<String>(this, R.layout.text, debitcard);
        a2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(a2);


        ArrayAdapter<String> a3 = new ArrayAdapter<String>(this, R.layout.text, netbanking);
        a3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(a3);

    }
}
