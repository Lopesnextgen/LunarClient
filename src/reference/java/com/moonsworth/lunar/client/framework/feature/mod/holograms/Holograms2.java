package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.listener.Nameplate;
import java.util.function.Predicate;

public interface Holograms2<T extends BridgeExtension> {
   <V extends T> Holograms2<V> method1(Class<V> var1);

   Holograms2<T> method2(Predicate<T> var1);

   Holograms3<T> method3(com.moonsworth.lunar.client.framework.listener.DynamicListener var1);

   Holograms3<T> method4(AbstractFeature var1);

   Holograms3<T> method5(com.moonsworth.lunar.client.framework.listener.DynamicListener var1, Nameplate var2);

   Holograms3<T> method6(AbstractFeature var1, Nameplate var2);
}
