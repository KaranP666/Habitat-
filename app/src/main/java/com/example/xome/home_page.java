package com.example.xome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class home_page extends AppCompatActivity {
    public FirebaseAuth firebaseAuth;
    public RecyclerView recyclerView;
    ViewAdapterModel viewAdapterModel;
    ArrayList<DataRetrieving> list;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;
    public ImageView imageView;
    //1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //id's


        //status bar color
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.pinkColor));
        }
        setContentView(R.layout.activity_home_page);



        MaterialToolbar materialToolbar = findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case R.id.nav_home:
                        Toast.makeText(home_page.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sell:
                        Intent intent1 = new Intent(home_page.this, sell_property.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.signout:
                                firebaseAuth.signOut();
                                Intent intent = new Intent(home_page.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

//2  Retrieving DATA
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//3
        list = new ArrayList<>();
        viewAdapterModel = new ViewAdapterModel(this,list);
        recyclerView.setAdapter(viewAdapterModel);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Property data");
        recyclerView.setHasFixedSize(true);

        firebaseStorage = FirebaseStorage.getInstance();

//        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
//        imageView = findViewById(R.id.bgIMG);
//        storageReference = firebaseStorage.getReference();
//        storageReference.child(firebaseAuth.getUid()).child("Images/Property Image").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri).fit().centerCrop().into(imageView);
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DataRetrieving dataRetrieving = dataSnapshot.getValue(DataRetrieving.class);
                    list.add(dataRetrieving);
                 }
                viewAdapterModel.notifyDataSetChanged();
                Toast.makeText(home_page.this,"Successful",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(home_page.this,""+error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void expand(View view){

    }
}