package com.moonsworth.lunar.client.feature;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.ThreadModuleDump91;
import java.util.Collection;

public interface Module {
   boolean method1();

   String getName();

   ResourceLocationBridge method2();

   ResourceLocationBridge method3();

   boolean method4();

   boolean isDynamic();

   ModuleType method5();

   Collection<ThreadModuleDump91> method6();

   void load(JsonObject var1);

   JsonObject save();
}
