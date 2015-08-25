package com.bubei.android.snap;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航界面
 */
public class IntroductionActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageView imageView;
    /** 创建一个View列表，用来存放每一页要显示的View */
    private List<View> pageViewList;
    /** 创建一个ImageView数组，用来表示导航的小圆点 */
    private ImageView[] imageViews;
    /** 装载图片的ViewGroup */
    private ViewGroup viewPictures;
    /** 导航小圆点的ViewGroup */
    private ViewGroup viewPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        pageViewList = new ArrayList<>();
        pageViewList.add(inflater.inflate(R.layout.viewpager01, null));
        pageViewList.add(inflater.inflate(R.layout.viewpager02, null));
        pageViewList.add(inflater.inflate(R.layout.viewpager03, null));

        // 加载
        viewPictures = (ViewGroup) inflater.inflate(R.layout.viewpagers, null);
        // 初始化小圆点图片数组
        imageViews = new ImageView[pageViewList.size()];

        viewPager = (ViewPager) viewPictures.findViewById(R.id.guidePagers);
        viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);

        // 添加小圆点导航的图片
        for (int i = 0; i < pageViewList.size(); i++) {
            imageView = new ImageView(IntroductionActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(5, 0, 5, 0);
            // 把小圆点放进数组中
            imageViews[i] = imageView;
            // 默认选中的是第一张图片，此时第一个小圆点是选中状态，其他不是
            if (i == 0) {
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_focused));
            } else {
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_unfocused));
            }
            // 将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews[i]);
        }

        setContentView(viewPictures);

        viewPager.setAdapter(new NavigationPageAdapter());
        // 为viewpager添加监听，当view发生变化时的响应
        viewPager.setOnPageChangeListener(new NavigationPageChangeListener());
    }

    // 导航图片view的适配器，必须要实现的是下面四个方法
    class NavigationPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化每个Item
        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(pageViewList.get(position));
            return pageViewList.get(position);
        }

        // 销毁每个Item
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(pageViewList.get(position));
        }

    }

    /**
     * viewpager的监听器，主要是onPageSelected要实现
     */
    class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 循环主要是控制导航中每个小圆点的状态
            for (int i = 0; i < imageViews.length; i++) {
                // 当前view下设置小圆点为选中状态
                imageViews[i].setImageDrawable(getResources().getDrawable(
                        R.drawable.page_indicator_focused));
                // 其余设置为飞选中状态
                if (position != i)
                    imageViews[i].setImageDrawable(getResources().getDrawable(
                            R.drawable.page_indicator_unfocused));
            }
        }

    }

    // 开始按钮方法，开始按钮在XML文件中onClick属性设置；
    // 我试图把按钮在本activity中实例化并设置点击监听，但总是报错，使用这个方法后没有报错，原因没找到
    public void startButton(View v) {
        Intent intent = new Intent(IntroductionActivity.this, IntroAnimationActivity.class);
        startActivity(intent);
        IntroductionActivity.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introduction, menu);
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
