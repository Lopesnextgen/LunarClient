package com.moonsworth.lunar.client.guiRewindhandlers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation3 {
   Class<? extends DynamicListener> value();
}
