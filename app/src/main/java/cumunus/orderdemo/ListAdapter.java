package cumunus.orderdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kunalkendrekar on 1/12/16.
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Product> productList;

    public ListAdapter(Context context,List<Product>productList)
    {
       this.context=context;
        this.productList=productList;
        inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null) {

            holder=new ViewHolder();
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            holder.img= (ImageView) convertView.findViewById(R.id.img);
            holder.txtTitle=(TextView)convertView.findViewById(R.id.txttitle);
            holder.txtDes=(TextView)convertView.findViewById(R.id.txtdes);
            holder.txtPrice=(TextView)convertView.findViewById(R.id.txtprice);
            holder.addButton=(Button)convertView.findViewById(R.id.btnadd);
            holder.layout_qty=(LinearLayout)convertView.findViewById(R.id.layout_qty);

            holder.layout_margin=(LinearLayout)convertView.findViewById(R.id.l1);
            holder.plus=(Button)convertView.findViewById(R.id.add);
            holder.minus=(Button)convertView.findViewById(R.id.minus);
            holder.txtqty=(TextView)convertView.findViewById(R.id.txtqty);


            convertView.setTag(holder);
        }
        holder= (ViewHolder) convertView.getTag();

        Product product=productList.get(position);
        holder.addButton.setVisibility(View.GONE);
        holder.layout_qty.setVisibility(View.GONE);

        if(product.getSelected_qty()>0)
        {
            holder.layout_qty.setVisibility(View.VISIBLE);
            holder.txtqty.setText(product.getSelected_qty()+"");
        }
        else
            holder.addButton.setVisibility(View.VISIBLE);

        holder.addButton.setTag(holder);
        holder.plus.setTag(holder);
        holder.minus.setTag(holder);


        if(position==getCount()-1 && CartUtils.getCart().size()>0)
            holder.layout_margin.setVisibility(View.VISIBLE);
        else
            holder.layout_margin.setVisibility(View.GONE);


        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewHolder holder1= (ViewHolder) v.getTag();

                holder1.addButton.setVisibility(View.GONE);
                holder1.layout_qty.setVisibility(View.VISIBLE);


                Product p = productList.get(position);
                p.setSelected_qty(p.getSelected_qty()+1);
                holder1.txtqty.setText(p.getSelected_qty() + "");

                CartUtils.addORreplaceProduct(p);
                ((MainActivity)context).changeCartIcon();

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product p=productList.get(position);

                ViewHolder holder1= (ViewHolder) v.getTag();

                p.setSelected_qty(p.getSelected_qty()+1);
                holder1.txtqty.setText(p.getSelected_qty() + "");

                CartUtils.addORreplaceProduct(p);
                ((MainActivity)context).changeCartIcon();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product p=productList.get(position);

                ViewHolder holder1= (ViewHolder) v.getTag();

                p.setSelected_qty(p.getSelected_qty() - 1);

                if(p.getSelected_qty()==0)
                {
                    holder1.addButton.setVisibility(View.VISIBLE);
                    holder1.layout_qty.setVisibility(View.GONE);

                    CartUtils.removeProduct(p);
                }


                holder1.txtqty.setText(p.getSelected_qty() + "");

                ((MainActivity)context).changeCartIcon();
            }
        });


        holder.txtTitle.setText(product.getProductname());
        holder.txtDes.setText(product.getDescription());
        holder.txtPrice.setText(context.getString(R.string.rupee)+" "+product.getPrice());

        switch (product.getId())
        {
            case 1:

                  holder.img.setImageResource(R.drawable.sandwich);
            break;

            case 2:

                holder.img.setImageResource(R.drawable.wrap);
                break;

            case 3:

                holder.img.setImageResource(R.drawable.salad);
                break;

            case 4:

                holder.img.setImageResource(R.drawable.biryani);
                break;
        }
        return convertView;
    }

    class ViewHolder
    {
        TextView txtTitle,txtDes,txtPrice;
        ImageView img;
        Button addButton,plus,minus;
        LinearLayout layout_qty,layout_margin;
        TextView txtqty;
    }

}
