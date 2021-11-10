package com.example.recyclerviewlesson;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private List<ContactsModel> list;

    public ContactsAdapter(Context context, List<ContactsModel> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ContactsViewHolder holder, int position) {
        holder.txtContactName.setText(list.get(position).getName());
        holder.txtContactPhone.setText(list.get(position).getPhone());
        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel", list.get(position).getPhone(), null));
            v.getContext().startActivity(intent);
        });
        holder.btnWhatsapp.setOnClickListener(v -> {
            String url = "https://api.whatsapp.com/send?phone=" + list.get(position).getPhone();
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse(url));
            v.getContext().startActivity(whatsappIntent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView txtContactName, txtContactPhone;
        Button btnCall, btnWhatsapp;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            txtContactName = itemView.findViewById(R.id.txt_item_contact_name);
            txtContactPhone = itemView.findViewById(R.id.txt_item_contact_phone);
            btnCall = itemView.findViewById(R.id.btn_item_contact_call);
            btnWhatsapp = itemView.findViewById(R.id.btn_item_contact_whatsapp);
        }
    }
}
