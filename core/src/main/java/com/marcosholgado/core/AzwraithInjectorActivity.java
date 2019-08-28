package com.marcosholgado.core;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.marcosholgado.core.di.AzwraithEntry;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class AzwraithInjectorActivity extends AppCompatActivity
        implements HasFragmentInjector, HasSupportFragmentInjector {

    //todo 需参考DaggerAppCompatActivity来实现Fragment注解的委派
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AzwraithInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return frameworkFragmentInjector;
    }

    public abstract AzwraithEntry getInjector();
}

