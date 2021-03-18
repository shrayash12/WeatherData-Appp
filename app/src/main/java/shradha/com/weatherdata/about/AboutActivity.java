package shradha.com.weatherdata.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import shradha.com.weatherdata.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivLinkedin;
    ImageView ivTwitter;
    ImageView ivGithub;
    ImageView ivBack;
    LinearLayout linearLayoutMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ivLinkedin = findViewById(R.id.ivLinkedin);
        ivTwitter = findViewById(R.id.ivTwitter);
        ivGithub = findViewById(R.id.ivGithub);
        ivBack = findViewById(R.id.ivBack);
        linearLayoutMentor = findViewById(R.id.yashwantContainer);
        ivLinkedin.setOnClickListener(this);
        ivTwitter.setOnClickListener(this);
        ivGithub.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        linearLayoutMentor.setOnClickListener(this);
        setImageToSocialMedia();
    }

    private void setImageToSocialMedia() {
        ivLinkedin.setBackground(new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_linkedin)
                .color(ContextCompat.getColor(this, R.color.white))
                .sizeDp(28));


        ivTwitter.setBackground(new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_twitter)
                .color(ContextCompat.getColor(this, R.color.white))
                .sizeDp(28));

        ivGithub.setBackground(new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_github_circle)
                .color(ContextCompat.getColor(this, R.color.white))
                .sizeDp(28));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLinkedin:
                Intent myProfile = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/shrayash12"));
                startActivity(myProfile);
                break;
            case R.id.ivTwitter:
                Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ShradhaRathod3"));
                startActivity(twitterIntent);
                break;
            case R.id.ivGithub:
                Intent github = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shrayash12"));
                startActivity(github);
                break;
            case R.id.yashwantContainer:
                Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/yashwant-chawhan-93930b56?trk=nav_responsive_tab_profile"));
                startActivity(linkedInIntent);
                break;
            case R.id.ivBack:
                onBackPressed();
                finish();
                break;
        }
    }
}