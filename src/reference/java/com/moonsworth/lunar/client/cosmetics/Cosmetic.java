package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import java.util.Collection;

public interface Cosmetic {
   boolean method1();

   String getName();

   ResourceLocationBridge method2();

   ResourceLocationBridge method3();

   boolean method4();

   boolean isDynamic();

   CosmeticSlot method5();

   Collection<ThreadModuleDump91> method6();

   void load(JsonObject json1);

   JsonObject save();
}
