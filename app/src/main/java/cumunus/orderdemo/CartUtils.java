package cumunus.orderdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kunalkendrekar on 1/13/16.
 */
public class CartUtils {

    private static List<Product> cartProdcuts=new ArrayList<>();

    public static void addORreplaceProduct(Product p)
    {

        if(cartProdcuts.contains(p))
        {
            int i=cartProdcuts.indexOf(p);
            cartProdcuts.set(i,p);
            return;
        }

        cartProdcuts.add(p);
    }

    public static void removeProduct(Product p)
    {
        cartProdcuts.remove(p);
    }

    public static List<Product> getCart()
    {
        return cartProdcuts;
    }

    public static void clearCart()
    {
        cartProdcuts.clear();
    }
}
