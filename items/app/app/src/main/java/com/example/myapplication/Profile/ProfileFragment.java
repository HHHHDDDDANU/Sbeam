package com.example.myapplication.Profile;
import com.example.myapplication.FriendsListActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.LibraryAndWishlist.MyLibrary;
import com.example.myapplication.LibraryAndWishlist.MyWishlist;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.example.myapplication.LoginAndSignup.SignActivity;

/**
 * @author u7574421 Simon Fu
 * This class defines the profile fragment, used to display the user's personal information.
 */
public class ProfileFragment extends Fragment {
    User user;
    ShapeableImageView profile;
    TextView username;
    TextView balance;
    Button wishlist;
    Button signout;
    Button library;
    Button friends;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.profile_fragment,container,false);
        profile=root.findViewById(R.id.profile);
        username=root.findViewById(R.id.profile_username);
        balance=root.findViewById(R.id.balance);
        wishlist=root.findViewById(R.id.button_wishlist);
        friends=root.findViewById(R.id.button_friends);
        signout=root.findViewById(R.id.button_signout);
        library=root.findViewById(R.id.button_library);
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
        //save profile img to cloud
        ActivityResultLauncher<String> uploadIMG = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {  // read the profile url from database if there is a change on data
                        if (uri != null) {
                            Glide.with(getActivity()).load(uri).into(profile);
                            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                            StorageReference imageRef = storageRef.child("images/" + uri.getLastPathSegment());
                            UploadTask uploadTask = imageRef.putFile(uri);

                            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                // upload a profile image
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }
                                    return imageRef.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                // save the uploaded image to database
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
                                        dbRef.child("profileUrl").setValue(downloadUri.toString());
                                    }
                                }
                            });
                        }
                    }
                });
        reference.addValueEventListener(new ValueEventListener() {
            // read the user's name from database, then load it to TextView
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String usernameString="";
                if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
                    usernameString = FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0];
                }
                username.setText("Hello, "+usernameString);
                user=dataSnapshot.getValue(User.class);
                balance.setText("$"+user.getBalance());
                Glide.with(getContext()).load(user.getProfileUrl()).into(profile);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
            }
        });
        // set onclick event for wishlist button
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MyWishlist.class);
                startActivity(intent);
            }
        });
        // set on click event for friends button
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FriendsListActivity.class);
                startActivity(intent);
            }
        });
        // set on click event for sign out button
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference user_reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
                user_reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // set the user status when sign out
                        User user = snapshot.getValue(User.class);
                        user.setStatus(0);
                        user_reference.setValue(user);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Firebase", "loadPost:onCancelled", error.toException());
                    }
                });
                // return to sign in page
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(), SignActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        // when click on profile image, start upload action
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadIMG.launch("image/*");
            }
        });
        // set the onclick event for library button
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MyLibrary.class);
                startActivity(intent);
            }
        });

        return root;
    }
}