package com.example.myapplication.LoginAndSignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainInterface;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author u7574421 Simon Fu
 * This class is used to define the login fragment, implementing the user login function.
 */
public class LoginFragment extends Fragment {
    EditText username,password;
    Button button;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.login_fragment,container,false);
        username=root.findViewById(R.id.login_userName);
        password=root.findViewById(R.id.login_passWord);
        button=root.findViewById(R.id.login_button);
        textView=root.findViewById(R.id.login_reset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    DatabaseReference user_reference = FirebaseDatabase.getInstance().getReference("users").child(uid);
                                    user_reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            User user = snapshot.getValue(User.class);
                                            user.setStatus(1);
                                            user_reference.setValue(user);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Log.w("Firebase", "loadPost:onCancelled", error.toException());
                                        }
                                    });
                                    Intent intent=new Intent(getActivity(), MainInterface.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ResetPassword.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
