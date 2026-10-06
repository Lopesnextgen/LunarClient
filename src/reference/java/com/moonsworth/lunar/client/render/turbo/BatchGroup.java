package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;

public interface BatchGroup<ID> extends Iterable<ID> {
   boolean method1(Vec3Bridge var1, ID var2);

   void method2(ID var1);

   void method3(ID var1);

   void clear();

   boolean method4();
}
