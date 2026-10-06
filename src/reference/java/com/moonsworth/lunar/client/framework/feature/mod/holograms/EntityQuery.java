package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import java.util.function.Predicate;

public interface EntityQuery<T extends MovementInputMarker> {
   <V extends T> EntityQuery<V> method1(Class<V> clazz1);

   EntityQuery<T> method2(Predicate<T> predicate1);

   EntitySubscription<T> method3(com.moonsworth.lunar.client.framework.listener.DynamicListener guirewindhandlershandler21);

   EntitySubscription<T> method4(AbstractFeature framework7extension21);

   EntitySubscription<T> method5(com.moonsworth.lunar.client.framework.listener.DynamicListener guirewindhandlershandler21, Nameplate nameplate2);

   EntitySubscription<T> method6(AbstractFeature framework7extension21, Nameplate nameplate2);
}
