package com.moonsworth.lunar.client.framework.feature.mod.holograms;

import com.moonsworth.lunar.bridge.MovementInputMarker;

interface EntityChangeListener<T extends MovementInputMarker> {
   void method1(T value1);

   void method2(T value1);

   void method3(T value1);

   void clear();
}
