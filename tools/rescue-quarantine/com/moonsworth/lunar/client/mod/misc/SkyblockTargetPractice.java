package com.moonsworth.lunar.client.mod.misc;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Bridge_56;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler22_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler23_2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.GuiRewindhandlersHandler26;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data9;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers2.Data3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers2.Data3.Type;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler25;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightImpl9;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class SkyblockTargetPractice extends Framework7Extension2 {
   private final GuiRewindhandlersHandler22_2 field8 = (GuiRewindhandlersHandler22_2)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler22_2.class);
   private final GuiRewindhandlersHandler23_2 field9 = (GuiRewindhandlersHandler23_2)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler23_2.class);
   private final GuiRewindhandlersHandler25 field10 = (GuiRewindhandlersHandler25)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler25.class);
   private final GuiRewindhandlersHandler26 field11 = (GuiRewindhandlersHandler26)this.IHRHHRIHICHOOICIRIOOHOICHIRHOI(GuiRewindhandlersHandler26.class);
   private static final int field12 = -1996554240;
   private static final int field13 = -2013200640;
   private static final int field14 = -1996488960;
   private final Set<Vector3i> field15;
   private final Set<Vector3i> field16;
   private final Vector3i field17 = new Vector3i(63, 127, 35);
   private final Set<Vector3i> field18 = new HashSet<>();
   private final Map<Vector3i, Integer> field19 = new Object2IntOpenHashMap();

   public SkyblockTargetPractice(SkyblockTerminalSolvers var1, LightingExtension443 var2) {
      super(true);
      this.method4(Framework.field16, Framework4.method3(var1));
      this.method4(Framework.field6, Framework3.method7(var2));
      this.handle(HighlightImpl9.class, this::method4);
      this.handle(Data9.class, var1x -> this.field18.clear());
      this.handle(Data3.class, this::method1);
      this.handle(HighlightImpl2.class, this::method2);
      Vector3i var3 = new Vector3i(68, 130, 50);
      HashSet var4 = new HashSet();

      for (byte var5 = 0; var5 <= 4; var5 += 2) {
         for (byte var6 = 0; var6 <= 4; var6 += 2) {
            var4.add(new Vector3i(var3.x - var5, var3.y - var6, var3.z));
         }
      }

      this.field15 = ImmutableSet.copyOf(var4);
      var4.clear();

      for (byte var7 = 1; var7 <= 4; var7 += 2) {
         for (byte var8 = 0; var8 <= 4; var8 += 2) {
            var4.add(new Vector3i(var3.x - var7, var3.y - var8, var3.z));
         }
      }

      this.field16 = ImmutableSet.copyOf(var4);
   }

   private void method1(Data3 var1) {
      if (this.method13()) {
         if (var1.method1() == Type.DEVICE && var1.method2().equals(this.field10.method5())) {
            Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
            if (!(var2.ORICHRORRORHORHOIHCRHOORCRRHOI(this.field17.x, this.field17.y, this.field17.z) > 225.0)) {
               this.field11
                  .method2(
                     ComparableImpl.method2()
                        .method2(Component.text("Device Done!").color(NamedTextColor.GOLD))
                        .method1("TARGET_PRACTICE_DEVICE_COMPLETE")
                        .method3(750L)
                        .method6()
                  );
               this.field18.clear();
               this.field18.addAll(this.field15);
            }
         }
      }
   }

   private void method2(HighlightImpl2 var1) {
      if (this.method13()) {
         Bridge2_43 var2 = ThreadModuleDump63.method13();
         BridgeExtension_9 var3 = var1.method3();
         var3.push();
         var3.translate(-var2.bridge$renderPosX(), -var2.bridge$renderPosY(), -var2.bridge$renderPosZ());

         for (Vector3i var5 : this.field18) {
            Click.method2(var3, var5, -1996554240);
         }

         for (Entry var7 : this.field19.entrySet()) {
            Click.method2(var3, (Vector3ic)var7.getKey(), (Integer)var7.getValue());
         }

         var3.pop();
      }
   }

   private boolean method13() {
      return this.field8.method6().getNumber() == 7;
   }

   private void method4(HighlightImpl9 var1) {
      if (this.method13()) {
         Bridge_56 var2 = Bridge.method34();
         Bridge3_23 var3 = var1.method3().bridge$getBlock();
         Vector3i var4 = var1.method1().bridge$toJoml();
         if (var4.equals(this.field17) && (var3 != var2.method110() || !var3.bridge$isDepressedPlate(var4.x, var4.y, var4.z))) {
            this.field18.clear();
            this.field19.clear();
         }

         if (this.field15.contains(var4)) {
            if (var3 == var2.method84()) {
               this.field19.clear();
               this.field19.put(var4, -2013200640);
               this.method6(var4);
               return;
            }

            if (var1.method2().bridge$getBlock() == var2.method84()) {
               this.field18.add(var4);
            }

            if (!this.method5(var2)) {
               this.field19.clear();

               for (Vector3i var6 : this.field16) {
                  if (this.method7(var6)) {
                     this.field19.put(var6, -1996488960);
                  }
               }
            }
         }
      }
   }

   private boolean method5(Bridge_56 var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 == null) {
         return false;
      }

      for (Vector3i var4 : this.field15) {
         if (var2.method5(var4) == var1.method84()) {
            return true;
         }
      }

      return false;
   }

   private void method6(Vector3i var1) {
      Vector3i var2 = new Vector3i(var1).sub(1, 0, 0);
      Vector3i var3 = new Vector3i(var1).add(1, 0, 0);
      if (this.method7(var2)) {
         this.field19.put(var2, -1996488960);
      }

      if (this.method7(var3)) {
         this.field19.put(var3, -1996488960);
      }
   }

   private boolean method7(Vector3i var1) {
      if (!this.field16.contains(var1)) {
         return false;
      }

      Vector3i var2 = new Vector3i(var1).sub(1, 0, 0);
      Vector3i var3 = new Vector3i(var1).add(1, 0, 0);
      return this.field15.contains(var2) && this.field15.contains(var3) && !this.field18.contains(var2) && !this.field18.contains(var3);
   }

   public String getId() {
      return "SKYBLOCK_TARGET_PRACTICE";
   }
}
