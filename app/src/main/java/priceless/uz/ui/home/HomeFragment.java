package priceless.uz.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import priceless.uz.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    Button sign_in_button_variable, sign_out_button_variable;
    TextView displayName, email;
    ImageView user_photo_variable;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        sign_in_button_variable = root.findViewById(R.id.sign_in_button);
        sign_out_button_variable = root.findViewById(R.id.sign_out_button);
        user_photo_variable = root.findViewById(R.id.userPhoto);

        sign_in_button_variable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        sign_out_button_variable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_out_button:
                        signOut();
                        break;
                    // ...
                }
            }
        });

        displayName = root.findViewById(R.id.displayName);
        email = root.findViewById(R.id.email);



        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);





        return root;
    }



    private void signIn() {
        Log.d("mine", "signIn clicked!");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Log.d("mine", "requestCode=" + requestCode);
//        Log.d("mine", "resultCode=" + resultCode);
//        Log.d("mine", "data=" + data);


        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
//            Log.d("mine", "requestCode == RC_SIGN_IN true");
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        Log.d("mine", "handleSignInResult going" );
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("mine", "account" + account);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            Log.d("mine", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null) {
//            String personName = account.getDisplayName();
//            String personGivenName = account.getGivenName();
//            String personFamilyName = account.getFamilyName();
//            String personEmail = account.getEmail();
//            String personId = account.getId();
//            Uri personPhoto = account.getPhotoUrl();
//            Log.w("Error", "personName=" + account.getDisplayName());

            displayName.setText(account.getDisplayName());
            email.setText(account.getEmail());
            Glide.with(getActivity()).load(String.valueOf(account.getPhotoUrl())).into(user_photo_variable);

            sign_in_button_variable.setVisibility(View.GONE);
            sign_out_button_variable.setVisibility(View.VISIBLE);
        }
        else
        {
            displayName.setText("name");
            email.setText("email");
            Glide.with(getActivity()).load("@mipmap/ic_launcher_round").into(user_photo_variable);
            sign_in_button_variable.setVisibility(View.VISIBLE);
            sign_out_button_variable.setVisibility(View.GONE);
        }

    }


    @Override
    public void onResume() {
        super.onResume();

// Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        updateUI(account);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        updateUI(account);


    }


    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        updateUI(null);
                    }
                });
    }


}