package com.bwie.xutils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {
    private String url = "http://api.kkmh.com/v1/daily/comic_lists/0?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3NzQyMjQwNjE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjEzIiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6ImJpZ25veCIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjAsIiRzY3JlZW5faGVpZ2h0IjoxMjgwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjAsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTEsIiRzY3JlZW5fd2lkdGgiOjcyMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNoaW5hIE1vYmlsZSIsIiRtb2RlbCI6IlZQaG9uZSIsIiRhcHBfdmVyc2lvbiI6IjMuNi4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo2YWRkYzdhZTQ1MjUwMzY1Iiwib3JpZ2luYWxfaWQiOiJBOjZhZGRjN2FlNDUyNTAzNjUiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
   @ViewInject(R.id.tv)
    private TextView tv;
    private RequestParams params;
    @ViewInject(R.id.progress)
    ProgressBar progress;
    @ViewInject(R.id.lv)
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        tv.setText("朱佳林最帅");


        params = new RequestParams("http://vf1.mtime.cn/Video/2016/09/15/mp4/160915092608935956_480.mp4");
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/mutouren1.mkv");
        params.setCancelFast(true);
        params.setAutoRename(false);
        x.http().get(params,new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String result) {
                Log.e("haha--------", result);
                Gson gson = new Gson();
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                final List<ResultBean.DataBean.ComicsBean> comics = resultBean.getData().getComics();
//                lv.setAdapter(new BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return comics.size();
//                    }
//
//                    @Override
//                    public Object getItem(int position) {
//                        return comics.get(position);
//                    }
//
//                    @Override
//                    public long getItemId(int position) {
//                        return position;
//                    }
//
//                    @Override
//                    public View getView(int position, View convertView, ViewGroup parent) {
//
//                        ImageOptions imageOptions = new ImageOptions.Builder().setCircular(true).build();
//                        convertView = View.inflate(MainActivity.this, R.layout.show, null);
//                        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
//                        ImageView img1 = (ImageView) convertView.findViewById(R.id.img1);
//                        tv1.setText(comics.get(position).getTitle());
//                        //Glide.with(MainActivity.this).load(comics.get(position).getCover_image_url()).into(img1);
//                        x.image().bind(img1,comics.get(position).getCover_image_url(),imageOptions);
//                        x.image().bind(img1, comics.get(position).getCover_image_url(), imageOptions, new CommonCallback<Drawable>() {
//                            @Override
//                            public void onSuccess(Drawable result) {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable ex, boolean isOnCallback) {
//
//                            }
//
//                            @Override
//                            public void onCancelled(CancelledException cex) {
//
//                            }
//
//                            @Override
//                            public void onFinished() {
//
//                            }
//                        });
//                        return convertView;
//                    }
//                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });





    }
    @Event(value = {R.id.but1,R.id.but2,R.id.but3,R.id.but4})
    private void hi(View v){
        switch (v.getId()){
            case R.id.but1:
                Toast.makeText(this, "but1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but2:
                Toast.makeText(this, "but2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but3:
                Toast.makeText(this, "but3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but4:
                Toast.makeText(this, "but4", Toast.LENGTH_SHORT).show();
                x.http().get(params, new Callback.ProgressCallback<File>() {


                    @Override
                    public void onSuccess(File file) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

                    @Override
                    public void onWaiting() {

                    }

                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {
                        progress.setMax((int) total);
                        progress.setProgress((int) current);
                    }
                });
                break;
        }
    }
}
