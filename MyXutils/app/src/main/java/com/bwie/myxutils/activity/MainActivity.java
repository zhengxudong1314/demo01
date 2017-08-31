package com.bwie.myxutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bwie.myxutils.R;
import com.bwie.myxutils.application.MyApplication;
import com.bwie.myxutils.bean.Student;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//加载布局文件
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    //找控件
    @ViewInject(R.id.img)
    private ImageView img;
    @ViewInject(R.id.lv)
    private ListView lv;
    @ViewInject(R.id.progress)
    private ProgressBar progress;
    private String img_url = "http://img2.imgtn.bdimg.com/it/u=3146783429,3997094047&fm=11&gp=0.jpg";
    private DbManager db;
    private List<Student> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化控件
        x.view().inject(this);
        db = MyApplication.getDbManager();


    }

    //点击事件
    @Event(value = {R.id.but1,R.id.but2,R.id.but3,R.id.but4,R.id.img})
    private void butClick(View view){
        switch (view.getId()){
            case R.id.but1:
                addData();
                break;
            case R.id.but2:
                queryData();
                break;
            case R.id.but3:
                upload();
                break;
            case R.id.but4:
                download();
                break;
            case R.id.img:
                loadImage();
                break;
        }
    }

    private void loadImage() {
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setRadius(30)
                .build();
        x.image().bind(img,img_url,options);

    }

    private void download() {
        RequestParams params = new RequestParams("http://169.254.222.214:8080/08web/xf.mp3");
        params.setAutoRename(true);
        params.setExecutor(new PriorityExecutor(3,true));
        params.setSaveFilePath("/mnt/sdcard/xf.mp3");
        params.setAutoResume(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progress.setVisibility(View.GONE);
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
    }

    private void upload() {
        RequestParams params = new RequestParams("http://169.254.222.214:8080/08web/FileUploadServlet");
        params.setMultipart(true);
        params.addBodyParameter("file",new File("/mnt/sdcard/mutouren1.mkv"));
        x.http().post(params, new Callback.CommonCallback<File>() {

            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
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
    //查询数据库
    private void queryData() {
        try {
            List<Student> students = db.findAll(Student.class);
            for (Student s:students) {
                Log.i("tag", s.toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    //添加数据
    private void addData() {
        Student student1 = new Student("郑旭东","男",18);
        Student student2 = new Student("朱佳林","男",22);
        Student student3 = new Student("张堃","男",15);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        try {
            db.save(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
