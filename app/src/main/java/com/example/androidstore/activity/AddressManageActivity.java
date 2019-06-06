package com.example.androidstore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.androidstore.R;

public class AddressManageActivity extends AppCompatActivity {
    private FloatingActionButton add_fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        add_fab=findViewById(R.id.fab_recycler_view);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressManageActivity.this, AddAddressActivity.class));
            }
        });
    }

}
