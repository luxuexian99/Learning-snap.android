package com.bubei.android.snap;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * 开场动画
 */
public class IntroAnimationActivity extends AppCompatActivity {

    private ImageView img_left, img_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_animation);
        img_left = (ImageView) findViewById(R.id.doorpage_left);
        img_right = (ImageView) findViewById(R.id.doorpage_right);

        // 创建一个AnimationSet对象
        AnimationSet animLeft = new AnimationSet(true);
        TranslateAnimation transLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                -1f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);

        // 设置动画效果持续的时间
        transLeft.setDuration(2000);
        // 将anim对象添加到AnimationSet对象中
        animLeft.addAnimation(transLeft);
        animLeft.setFillAfter(true);
        img_left.startAnimation(transLeft);
        transLeft.startNow();


        // 创建一个AnimationSet对象
        AnimationSet animRight = new AnimationSet(true);
        TranslateAnimation transRight = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                1f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        // 设置动画效果持续的时间
        transRight.setDuration(2000);
        // 将anim对象添加到AnimationSet对象中
        animRight.addAnimation(transRight);
        animRight.setFillAfter(true);
        img_right.startAnimation(transRight);
        transRight.startNow();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(IntroAnimationActivity.this, MainActivity.class);
                startActivity(intent);
                IntroAnimationActivity.this.finish();
            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introduction_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
