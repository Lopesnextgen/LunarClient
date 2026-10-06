package com.moonsworth.lunar.client.framework.listener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

@Retention(RetentionPolicy.RUNTIME)
public @interface TriggeredBy {
   Class<? extends DynamicListener> value();
}
