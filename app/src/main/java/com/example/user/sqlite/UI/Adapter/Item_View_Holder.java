package com.example.user.sqlite.UI.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.sqlite.R;

/**
 * Created by User on 09.10.2016.
 */
public class Item_View_Holder extends RecyclerView.ViewHolder
{
    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewSurname;
    private TextView textViewAge;

    public Item_View_Holder(View itemview)
    {
        super(itemview);
        textViewId = (TextView) itemview.findViewById(R.id.textViewId);
        textViewName = (TextView) itemview.findViewById(R.id.textViewName);
        textViewSurname = (TextView) itemview.findViewById(R.id.textViewSurName);
        textViewAge = (TextView) itemview.findViewById(R.id.textViewAge);
    }

    public TextView getTextViewId()
    {
        return textViewId;
    }

    public TextView getTextViewName()
    {
        return textViewName;
    }

    public TextView getTextViewSurName()
    {
        return textViewSurname;
    }

    public TextView getTextViewAge()
    {
        return textViewAge;
    }
}
