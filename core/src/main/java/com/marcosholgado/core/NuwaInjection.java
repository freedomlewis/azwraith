package com.marcosholgado.core;

import android.app.Activity;

import com.marcosholgado.core.di.NuwaInjector;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

import static dagger.internal.Preconditions.checkNotNull;

public final class NuwaInjection {
    private static final String TAG = "dagger.android";

    public static void inject(NuwaInjectorActivity activity) {
        checkNotNull(activity, "activity");
        NuwaInjector injector = activity.getInjector();
        if (injector == null) {
            throw new RuntimeException(
                    String.format(
                            "%s does not implement %s",
                            injector.getClass().getCanonicalName(),
                            HasActivityInjector.class.getCanonicalName()));
        }

        AndroidInjector<Activity> activityInjector =
                ((HasActivityInjector) injector).activityInjector();
        checkNotNull(activityInjector, "%s.activityInjector() returned null", injector.getClass());

        activityInjector.inject(activity);
    }

    private NuwaInjection() {
    }
}

