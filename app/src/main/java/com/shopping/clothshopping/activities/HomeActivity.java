package com.shopping.clothshopping.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.shopping.clothshopping.Adapters.TabAdapter;
import com.shopping.clothshopping.Data.Constants;
import com.shopping.clothshopping.OfferTab;
import com.shopping.clothshopping.R;
import com.shopping.clothshopping.Track_order;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    boolean isBackPressed = false;

    private static final String TAG = "";
    int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    TabLayout tlMain;
    ViewPager vpMain;
    NavigationView navigationView;
    DrawerLayout drawer;
    ImageView ivNavigation, ivProfile, ivLogout;
    TextView tvUserEmail, tvUserName;
    LinearLayout llTrackOrder,llProfile,llRating,llSupport,llShare;
    GoogleSignInClient mGoogleSignInClient;
    RecyclerView rvOfferTab,rvOfferTabInner;
    LinearLayout llOfferTabInner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);






        ivNavigation = findViewById(R.id.ivNavigation);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        ivProfile = headerView.findViewById(R.id.ivProfile);
        tvUserEmail = headerView.findViewById(R.id.tvUserEmail);
        tvUserName = headerView.findViewById(R.id.tvUserName);
        ivLogout = headerView.findViewById(R.id.ivLogout);
        llTrackOrder = headerView.findViewById(R.id.llTrackOrder);
        llProfile=headerView.findViewById(R.id.llProfile);
        llRating=headerView.findViewById(R.id.llRating);
        llShare=headerView.findViewById(R.id.llShare);
        llSupport=headerView.findViewById(R.id.llSupport);


        tlMain = findViewById(R.id.tlMain);
        vpMain = findViewById(R.id.vpMain);

        tlMain.setupWithViewPager(vpMain);
        vpMain.setAdapter(new TabAdapter(this, getSupportFragmentManager()));



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
              window.setStatusBarColor(Color.parseColor("#039BE5"));
        }


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        String id=null;
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        String name=null;
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);



        //listners

        ivNavigation.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivLogout.setOnClickListener(this);
        llTrackOrder.setOnClickListener(this);
        llRating.setOnClickListener(this);
        llShare.setOnClickListener(this);
        llSupport.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hideView(OfferTab.ViewEvent viewEvent) {
         rvOfferTab = viewEvent.getRvOfferTab();
        rvOfferTabInner = viewEvent.getRvOfferTabInner();
       llOfferTabInner = viewEvent.getLlOfferTabInner();
    }




    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClickClickedID: "+view.getId());
        switch (view.getId()){
            case R.id.ivNavigation:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.ivProfile:
                signIn();
                break;
            case R.id.ivLogout:
                signOut();
                break;
            case R.id.llTrackOrder:
                Log.d(TAG, "trackOrderID: "+R.id.llTrackOrder);
                Intent intent = new Intent(HomeActivity.this, Track_order.class);
                HomeActivity.this.startActivity(intent);
                break;
            case R.id.llRating:
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                break;

            case R.id.llShare:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Grocery Fresh");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + this.getPackageName();
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
                break;

            case  R.id.llSupport:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "ldhankher@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Fashion Counter: Online Cloth Shopping");
                this.startActivity(Intent.createChooser(emailIntent, null));

        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Login Failed: ", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            llProfile.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            tvUserName.setText(user.getDisplayName());
            tvUserEmail.setVisibility(View.VISIBLE);
            tvUserEmail.setText(user.getEmail());

            // Loading profile image
            Uri profilePicUrl = user.getPhotoUrl();
            if (profilePicUrl != null) {
                Glide.with(this).load(profilePicUrl).centerCrop()
                        .into(ivProfile);
            }
            ivLogout.setVisibility(View.VISIBLE);
            ivProfile.setClickable(false);
        }
        else {
            tvUserName.setVisibility(View.GONE);
            tvUserEmail.setVisibility(View.GONE);
            ivLogout.setVisibility(View.GONE);
            llProfile.setVisibility(View.GONE);
            ivProfile.setImageResource(R.drawable.profile);
            ivProfile.setClickable(true);

        }
    }


    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (Constants.isOfferButtonClicked) {
            Constants.isOfferButtonClicked=false;
            rvOfferTab.setVisibility(View.VISIBLE);
            llOfferTabInner.setVisibility(View.GONE);
            rvOfferTabInner.setVisibility(View.GONE);
        }else if(isBackPressed){
                        super.onBackPressed(); }
        else {
            isBackPressed=true;
            Toast.makeText(this, "Press Back Again to Exit", Toast.LENGTH_SHORT).show();
        }
    }
}

