package com.example.a0001.Helper;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.view.ActionBarPolicy;

import com.example.a0001.PopularDomain;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tyneDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tyneDB=new TinyDB(context);
    }
    public void insertFood(PopularDomain itens) {
        ArrayList<PopularDomain> listpop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listpop.size(); i++) {
            if (listpop.get(i).getTitle().equals(itens.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listpop.get(n).setNumberInCart(itens.getNumberInCart());
        }else{
            listpop.add(itens);
        }
        tyneDB.putListObject("Carrinho",listpop);
        Toast.makeText(context, "Adicionado ao seu carrinho", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart(){
        return tyneDB.getListObject("Carrinho");
    }

    public Double getTotalFee() {
        ArrayList<PopularDomain> listItem = getListCart();
        double fee=0;
        for (int i = 0; i < listItem.size(); i++) {
            fee=fee+(listItem.get(i).getPrice()*listItem.get(i).getNumberInCart());

        }
        return fee;

    }
    public void minusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener listener) {
        if (listItem.get(position).getNumberInCart() == 1) {
            listItem.remove(position);
        } else {
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() - 1);
        }
        tyneDB.putListObject("Carrinho", listItem);
        listener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener listener) {
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() + 1);
        tyneDB.putListObject("Carrinho", listItem);
        listener.change();
    }

}

