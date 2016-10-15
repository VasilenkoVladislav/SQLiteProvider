package com.example.user.sqlite.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sqlite.Model.Person;
import com.example.user.sqlite.R;

import java.util.ArrayList;

/**
 * Created by User on 09.10.2016.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<Item_View_Holder>
{

    private Context mContext;
    private ArrayList<Person> listperson;

    @Override
    public Item_View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item,parent,false);
        return new Item_View_Holder(view);
    }

    public AdapterRecyclerView(ArrayList<Person> listperson, Context mContext)
    {
        this.listperson = listperson;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(Item_View_Holder holder, int position)
    {
        Person person=(Person)listperson.get(position);
        holder.getTextViewId().setText(String.valueOf(person.getId()));
        holder.getTextViewName().setText(person.getName());
        holder.getTextViewSurName().setText(person.getSurname());
        holder.getTextViewAge().setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getItemCount()
    {
        return listperson.size();
    }
}
