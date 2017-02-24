package com.james.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

/**
 * 服务端实现一个Handler，由其接受来自客户端的每个调用的回调
 * 使用实现的Handler创建Messenger对象
 * 通过Messenger得到一个IBinder对象，并将其通过onBind()返回给客户端
 */
public class MessengerService extends Service {
    private Messenger mMessenger = new Messenger(new MessengerHanlder());

    private class MessengerHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MessengerService.this, "我是服务端,我收到了客户端发过来的随机数" + msg.what,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "客户端绑定我(远程服务端)成功", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }
}
