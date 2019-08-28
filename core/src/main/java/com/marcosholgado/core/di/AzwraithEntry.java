package com.marcosholgado.core.di;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasContentProviderInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.HasServiceInjector;

public abstract class AzwraithEntry
        implements HasActivityInjector,
        HasFragmentInjector,
        HasServiceInjector,
        HasBroadcastReceiverInjector,
        HasContentProviderInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;
    @Inject
    DispatchingAndroidInjector<ContentProvider> contentProviderInjector;
    private volatile boolean needToInject = true;

    public void onCreate() {
        injectIfNecessary();
    }

    protected abstract AndroidInjector<? extends AzwraithEntry> applicationInjector();

    private void injectIfNecessary() {
        if (needToInject) {
            synchronized (this) {
                if (needToInject) {
                    @SuppressWarnings("unchecked")
                    AndroidInjector<AzwraithEntry> applicationInjector =
                            (AndroidInjector<AzwraithEntry>) applicationInjector();
                    applicationInjector.inject(this);
                    if (needToInject) {
                        throw new IllegalStateException(
                                "The AndroidInjector returned from applicationInjector() did not inject the "
                                        + "DaggerApplication");
                    }
                }
            }
        }
    }

    @Inject
    void setInjected() {
        needToInject = false;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverInjector;
    }

    @Override
    public DispatchingAndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    @Override
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        injectIfNecessary();
        return contentProviderInjector;
    }
}
