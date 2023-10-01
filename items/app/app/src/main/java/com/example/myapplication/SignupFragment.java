package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class SignupFragment extends Fragment {
    EditText username,password,confirm;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.signup_fragment,container,false);
        username=root.findViewById(R.id.signup_userName);
        password=root.findViewById(R.id.signup_passWord);
        confirm=root.findViewById(R.id.signup_confirmPassWord);
        button=root.findViewById(R.id.signup_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().equals(confirm.getText().toString())) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information.
                                        Toast.makeText(getActivity(), "Sign up successfully", Toast.LENGTH_SHORT).show();
                                        username.setText("");
                                        password.setText("");
                                        confirm.setText("");
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(getActivity(), "password is inconsistency", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}
