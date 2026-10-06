package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl7;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl10;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl11;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl12;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl13;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl14;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl15;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl16;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl17;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl8;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl9;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nameplate {
   private static final Map<Integer, List<Class<? extends Nameplate2>>> field1 = new HashMap<>();

   public static int method1(int var0, Class<? extends Nameplate2> var1) {
      return field1.get(var0).indexOf(var1);
   }

   public static Nameplate2 method2(int var0, int var1) {
      try {
         return field1.get(var0).get(var1).newInstance();
      } catch (InstantiationException | IllegalAccessException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   static {
      field1.put(
         0,
         List.of(
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.colorsaturation.Nameplate2Impl.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl3.class,
            Nameplate2Impl17.class,
            Nameplate2Impl16.class,
            Nameplate2Impl12.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl2.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl.class,
            Nameplate2Impl10.class,
            Nameplate2Impl8.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl3.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl6.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl5.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl2.class,
            Nameplate2Impl11.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl4.class,
            Nameplate2Impl7.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl3.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl.class,
            Nameplate2Impl15.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl2.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl5.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl2.class,
            Nameplate2Impl14.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl7.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl4.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers.Nameplate2Iterator.class,
            Nameplate2Impl2.class,
            Nameplate2Impl.class,
            Nameplate2Impl_2.class,
            Nameplate2Iterator5.class,
            Nameplate2Task.class,
            Nameplate2Impl3.class,
            Nameplate2Impl4.class,
            Nameplate2Impl5.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl6.class,
            Nameplate2Impl13.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl.class,
            Nameplate2Iterator3.class,
            Nameplate2Iterator6.class,
            Nameplate2Iterator2.class,
            Nameplate2Iterator.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.rewindhandlers.Nameplate2Iterator2.class,
            Nameplate2Impl6.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.Nameplate2Impl.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl3.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl4.class,
            com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.Nameplate2Impl2.class,
            Nameplate2Iterator4.class,
            Nameplate2Impl9.class
         )
      );
   }
}
