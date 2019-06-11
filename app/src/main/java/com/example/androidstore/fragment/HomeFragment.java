package com.example.androidstore.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidstore.R;
import com.example.androidstore.activity.GoodsListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends Fragment {

    private Unbinder bind;

//    @BindView(R.id.recyclerview)
//    RecyclerView mRecyclerView;
//
//    private Banner mBanner;
//    private List<String> images = new ArrayList<>();
//    private List<String>           titles = new ArrayList<>();
//    private Gson gson   = new Gson();
//
//    View viewHeader;

    @BindView(R.id.vp)
    View mView;
    //   @BindView(R.id.bannerViewPager)
    // ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置  
    private int oldPosition = 0;
    //存放图片的id  
    private int[] imageIds = new int[]{
            R.drawable.adver1,
            R.drawable.adver2,
            R.drawable.adver3,
            R.drawable.adver4,
            R.drawable.adver5,
            R.drawable.adver6
    };
    //存放图片的标题  
    private String[] titles = new String[]{"轮播1", "轮播2", "轮播3", "轮播4"
    };
    private TextView title;
    /**
     *
     */
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bind = ButterKnife.bind(this, view);
//        init();
        setView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    public ViewPager mViewPaper;

    private void setView() {


        mViewPaper = mView.findViewById(R.id.vp);

        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));
        dots.add(mView.findViewById(R.id.dot_5));
        dots.add(mView.findViewById(R.id.dot_6));

        title = (TextView) mView.findViewById(R.id.title);
//        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
//
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /*定义的适配器*/
    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                3,
                TimeUnit.SECONDS);
    }


    /**
     * 图片轮播任务
     *
     * @author liuyazhuang
     */
    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }

        ;
    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }


    @OnClick({R.id.Tshit, R.id.sjkh, R.id.ssfs, R.id.shipin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Tshit:
                Intent intent = new Intent(getContext(), GoodsListActivity.class);
                intent.putExtra("CATEGORY_ID", (long) 125);
                getContext().startActivity(intent);
                break;
            case R.id.sjkh:
                break;
            case R.id.ssfs:
                break;
            case R.id.shipin:
                intent = new Intent(getContext(), GoodsListActivity.class);
                intent.putExtra("CATEGORY_ID", (long) 111);
                getContext().startActivity(intent);
                break;
            default:

        }
    }

}