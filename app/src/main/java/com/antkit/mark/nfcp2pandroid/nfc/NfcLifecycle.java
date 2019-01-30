package com.antkit.mark.nfcp2pandroid.nfc;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author Mark
 * @Date on 2019/1/30
 **/
public interface NfcLifecycle extends LifecycleObserver {
/*    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate( LifecycleOwner owner);*/

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    void onResume(LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onPause(LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestory(LifecycleOwner owner);
/*    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifeCycleChanged( LifecycleOwner owner,  Lifecycle.Event event);*/
}
