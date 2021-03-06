package org.fundsofhope.android.ui.ngo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.fundsofhope.android.R;
import org.fundsofhope.android.data.api.ApiInterface;
import org.fundsofhope.android.util.ServiceGeneratorUtil;
import org.fundsofhope.android.data.model.Ngo;
import org.fundsofhope.android.data.model.NgoDescription;
import org.fundsofhope.android.util.NetworkUtil;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by anip on 16/12/16.
 */
public class NgoDescriptionActivity extends AppCompatActivity {
    private Ngo ngo;
    ImageView profile;
    private TextView description;
    private NgoDescription ngoDescription;
    ViewPager mViewPager;
    private FloatingActionButton donate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngo_description);
        Gson gson = new Gson();
        ngo = gson.fromJson(getIntent().getStringExtra("ngo"), Ngo.class);
        getSupportActionBar().setTitle(ngo.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (NetworkUtil.isInternet(NgoDescriptionActivity.this)) {
            ApiInterface apiInterface = ServiceGeneratorUtil.createService(ApiInterface.class);
            apiInterface.ngo_detail(ngo.getId(),
                    new retrofit.Callback<NgoDescription>() {

                        @Override
                        public void success(final NgoDescription ngo_description, Response response) {
                            ngoDescription = ngo_description;
                            description.setText(ngo_description.getEmail());
                            Picasso.with(getApplicationContext()).load("http://api.fundsofhope.org"+ngo_description.getProfile()).into(profile);

                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("TAG", "Response : Failure " + error.getMessage());
                        }
                    });
        } else {
        }
        description = (TextView) findViewById(R.id.description);
        profile = (ImageView) findViewById(R.id.profile);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
