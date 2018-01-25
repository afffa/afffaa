package com.lbs.okhttp1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doGetClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //实例化OKhttpclick
                    OkHttpClient okhttpclick = new OkHttpClient();
                     Request request =  new Request.Builder().url("http://apicloud.mob.com/v1/weather/type?key=22ecf6c32440e").build();
                    //通过newCall方法获取到Call对象
                      Call call = okhttpclick.newCall(request);
                    //通过call去进行网络请求并返回响应结果
                    Response execute = call.execute();
                    if (execute.isSuccessful()) {
                        //获取到响应体
                        ResponseBody body = execute.body();
                        //根据相应体获取到相应内容
                        String jsonStr = body.string();
                        Log.i("t", "result=" + jsonStr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void doGetClickYi(View view){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://apicloud.mob.com/v1/weather/type?key=22ecf6c32440e").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    String string = body.string();
                    Log.i("tt",""+string);
                }
            }
        });
    }

    public void doPostClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient3 = new OkHttpClient();
                    FormBody.Builder add = new FormBody.Builder()
                                                        .add("mobile", "17611200379")
                                                        .add("password", "123456");
                    Request buildd = new Request.Builder().post(add.build()).url("http://120.27.23.105/user/login").build();
                    Call call = okHttpClient3.newCall(buildd);

                    Response execute = call.execute();

                    if (execute.isSuccessful()) {
                        ResponseBody body = execute.body();
                        String string = body.string();
                        Log.i("ttt",""+string);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void doPostClickYi(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody.Builder add = new FormBody.Builder()
                        .add("mobile", "17611200379")
                        .add("password", "123456");
                Request build = new Request.Builder().post(add.build()).url("http://120.27.23.105/user/login").build();
                Call call = okHttpClient.newCall(build);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()){
                            ResponseBody body = response.body();
                            String string = body.string();
                            Log.i("tttt",""+string);
                        }
                    }
                });

            }
        }).start();

    }



}
