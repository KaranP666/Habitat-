package com.example.xome;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class sell_property extends AppCompatActivity {

    public EditText Ownerr, Prop_name, Price, Address, Pincode, city, state;
    public FirebaseDatabase firebaseDatabase;
    public String Ownerofprop, Propname, Priceofprop,BHKs,Type, Addressofprop, Pincodeofprop, cityofprop, stateofprop;
    public Button PostAd;
    public AutoCompleteTextView autoCompleteTextView,autoComplete;
    DatabaseReference myRef;
    FirebaseAuth firebaseAuth;
    ImageView cover;
    FloatingActionButton fab;
    public FirebaseStorage firebaseStorage;
    public StorageReference storageReference;
    public Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_property);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.pinkColor));
        }
        //Declaring id's

        Ownerr = (EditText) findViewById(R.id.Owner);
        Prop_name = (EditText) findViewById(R.id.Prop_name);
        Price = (EditText) findViewById(R.id.Price);
        Address = (EditText) findViewById(R.id.Address);
        Pincode = (EditText) findViewById(R.id.Pincode);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        PostAd = (Button) findViewById(R.id.post);
        autoCompleteTextView =(AutoCompleteTextView) findViewById(R.id.filled);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.filled_e);
        cover =(ImageView) findViewById(R.id.coverimg);
        fab = (FloatingActionButton) findViewById(R.id.ftab);

        firebaseStorage =FirebaseStorage.getInstance();
        storageReference= firebaseStorage.getReference();



//for custom spinner
        String[] type = new String[]{"1", "2", "3", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type
        );
        autoCompleteTextView.setAdapter(adapter);

        String[] type1 = new String[]{"Sell", "Rent"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                type1
        );
        autoComplete.setAdapter(adapter1);


//for FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.with(sell_property.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });


//Uploading the data to firebase
        PostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                firebaseDatabase = FirebaseDatabase.getInstance();
                myRef =  firebaseDatabase.getReference().child("Property data").child(firebaseAuth.getUid());

                StorageReference imageReference= storageReference.child(firebaseAuth.getUid()).child("Images").child("Property Image"); //user_id/images/property_image.png
                UploadTask uploadTask = imageReference.putFile(uri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(sell_property.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sell_property.this,home_page.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(sell_property.this, "Upload failed", Toast.LENGTH_SHORT).show();
                    }
                });
                property_data property_data=new property_data(Ownerofprop,Propname,Priceofprop,BHKs,Type,Addressofprop,Pincodeofprop,cityofprop,stateofprop);
                myRef.setValue(property_data);


            }
        });
    }


    private Boolean validate(){
        Boolean result = false;

        Ownerofprop = Ownerr.getText().toString();
        Propname = Prop_name.getText().toString();
        Priceofprop= Price.getText().toString();
        BHKs = autoCompleteTextView.getText().toString();
        Type = autoComplete.getText().toString();
        Addressofprop= Address.getText().toString();
        Pincodeofprop= Pincode.getText().toString();
        cityofprop= city.getText().toString();
        stateofprop= state.getText().toString();

        if(Ownerofprop.isEmpty() || Propname.isEmpty() || Priceofprop.isEmpty() || Addressofprop.isEmpty() || Pincodeofprop.isEmpty() || cityofprop.isEmpty() || stateofprop.isEmpty() ){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        cover.setImageURI(uri);
    }
}