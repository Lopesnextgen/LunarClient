package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;

@Annotation2(min = 1)
public class MixinHelper2 extends MixinHelper2_3 {
   private final Map<String, Map<String, MixinHelper_3>> widgetStates = new HashMap<>();
   private int openWidgetCount = 0;
   private final Set<String> updatedWidgets = new HashSet<>();
   private String activeWidget = null;

   public MixinHelper2(MixinHelper var1) {
      super(var1);
   }

   @Override
   public void start() {
      this.openWidgetCount = 0;
      this.updatedWidgets.clear();
   }

   @Override
   public void end() {
      Map var1 = this.widgetStates.get(this.method15());
      if (var1 != null) {
         ArrayList var2 = new ArrayList();

         for (String var4 : var1.keySet()) {
            if (!this.updatedWidgets.contains(var4)) {
               var2.add(var4);
            }
         }

         for (String var6 : var2) {
            ((MixinHelper_3)var1.remove(var6)).delete();
         }
      }
   }

   public void clearGui(String var1) {
      Map var2 = this.widgetStates.get(var1);
      if (var2 != null) {
         for (MixinHelper_3 var4 : var2.values()) {
            var4.delete();
         }
      }

      this.widgetStates.remove(var1);
   }

   public void removeWidget(String var1, String var2) {
      Map var3 = this.widgetStates.get(var1);
      if (var3 != null) {
         MixinHelper_3 var4 = (MixinHelper_3)var3.get(var2);
         if (var4 != null) {
            var4.delete();
            var3.remove(var1);
         }
      }
   }

   public MixinHelper_3 method3(String var1) {
      Map var2 = this.widgetStates.computeIfAbsent(this.method15(), var0 -> new HashMap<>());
      return (MixinHelper_3)var2.get(var1);
   }

   private MixinHelper_3 getOrCreateWidget(String var1, int var2, int var3, MixinHelper2 var4) {
      this.updatedWidgets.add(var1);
      Map var5 = this.widgetStates.computeIfAbsent(this.method15(), var0 -> new HashMap<>());
      MixinHelper_3 var6 = (MixinHelper_3)var5.get(var1);
      if (var6 == null) {
         var6 = new MixinHelper_3(var1, var2, var3, var4);
         var5.put(var1, var6);
      }

      return var6;
   }

   public void beginWidget(String var1, int var2, int var3, int var4, int var5) {
      if (this.isSupported()) {
         if (!this.method3().method5(var2, var3, var4, var5)) {
            this.activeWidget = var1;
            if (this.openWidgetCount++ <= 1) {
               MixinHelper_3 var6 = this.getOrCreateWidget(var1, var4, var5, this);
               var6.method1(var2, var3, var4, var5);
               this.field1.method3();
               if (!var6.isDirty) {
                  this.field1.method12(false);
               } else {
                  this.method3().update();
                  var6.method2();
                  this.field1.method13(false);
               }
            }
         }
      }
   }

   private boolean isSupported() {
      return false;
   }

   public void endWidget(String var1) {
      if (this.isSupported()) {
         if (var1.equals(this.activeWidget)) {
            this.activeWidget = null;
            if (this.openWidgetCount-- <= 1) {
               MixinHelper_3 var2 = this.method3(var1);
               if (var2.isDirty) {
                  var2.method3();
                  var2.isDirty = false;
               }

               this.field1.method4();
               var2.method4();
            }
         }
      }
   }

   public void invalidateWidget(String var1, String var2) {
      Map var3 = this.widgetStates.get(var1);
      if (var3 != null) {
         MixinHelper_3 var4 = (MixinHelper_3)var3.get(var2);
         if (var4 != null) {
            var4.isDirty = true;
         }
      }
   }

   public void invalidateWidget(String var1) {
      MixinHelper_3 var2 = this.method3(var1);
      if (var2 != null) {
         var2.isDirty = true;
      }
   }

   public MixinHelper3 getRenderState() {
      return this.getRenderState();
   }

   @Generated
   public int method18() {
      return this.openWidgetCount;
   }
}
