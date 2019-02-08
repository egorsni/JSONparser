package com.example.user.json.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.json.R;
import com.example.user.json.model.entities.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UsersRecycleAdapter extends RecyclerView.Adapter<UsersRecycleAdapter.UsersVH> {

    List<User> usersList;

    public UsersRecycleAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersVH onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.user_item, viewGroup, false);

        return new UsersVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersVH UsersVH, final int i) {
        User user = usersList.get(i);


        UsersVH.userId.setText(user.getId()+"");
        UsersVH.userName.setText(user.getName());
        UsersVH.userNameName.setText(user.getUsername());
        UsersVH.userEmail.setText(user.getEmail());
        UsersVH.addressStreet.setText("Street: "+user.getAddress().getStreet());
        UsersVH.addressSuite.setText("Suite: " + user.getAddress().getSuite());
        UsersVH.addressCity.setText("City: " + user.getAddress().getCity());
        UsersVH.addressZipcode.setText("Zipcode: " + user.getAddress().getZipcode());
        UsersVH.geo.setText(user.getAddress().getGeo().toString());
        UsersVH.userPhone.setText("Phone: " + user.getPhone());
        UsersVH.userWebSite.setText("Website: " + user.getWebsite());
        UsersVH.companyName.setText("Company name: " + user.getCompany().getName());
        UsersVH.companyCatchPhrase.setText("Company catch phrase: " + user.getCompany().getCatchPhrase());
        UsersVH.companyBs.setText("Company bs: " + user.getCompany().getBs());
        UsersVH.userCard.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    usersList.remove(i);
                                                    notifyItemRemoved(i);
                                                    notifyItemChanged(i);
                                                }
                                            }
        );
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UsersVH extends RecyclerView.ViewHolder {
        private TextView userId;
        private TextView userName;
        private TextView userNameName;
        private TextView userEmail;
        private TextView addressStreet;
        private TextView addressSuite;
        private TextView addressCity;
        private TextView addressZipcode;
        private TextView geo;
        private TextView userPhone;
        private TextView userWebSite;
        private TextView companyName;
        private TextView companyCatchPhrase;
        private TextView companyBs;
        private CardView userCard;



        public UsersVH(@NonNull View itemView) {
            super(itemView);


            userId = itemView.findViewById(R.id.userId);
            userName = itemView.findViewById(R.id.userName);
            userNameName = itemView.findViewById(R.id.userNameName);
            userEmail = itemView.findViewById(R.id.userEmail);
            addressStreet = itemView.findViewById(R.id.addressStreet);
            addressSuite = itemView.findViewById(R.id.addressSuite);
            addressCity = itemView.findViewById(R.id.addressCity);
            addressZipcode = itemView.findViewById(R.id.addressZipcode);
            geo = itemView.findViewById(R.id.geo);
            userPhone = itemView.findViewById(R.id.userPhone);
            userWebSite = itemView.findViewById(R.id.userWebSite);
            companyName = itemView.findViewById(R.id.companyName);
            companyCatchPhrase = itemView.findViewById(R.id.companyCatchPhrase);
            companyBs = itemView.findViewById(R.id.companyBs);
            userCard = itemView.findViewById(R.id.userCard);

        }
    }
}