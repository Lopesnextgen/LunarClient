package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.combat.attackindicator.AttackIndicator;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Attackindicator {
   private final Map<ToggleOption, Attackindicator2> field1 = new LinkedHashMap<>();
   private final Set<String> field2 = new HashSet<>();
   private final Attackindicator3 field3 = new Attackindicator3(new Attackindicator.Data(), null);
   @Nullable
   private Attackindicator3 field4 = null;

   public Attackindicator() {
      this.method2("sleep", new Attackindicator2Handler());
      this.method2("blockBreaking", new Attackindicator2Handler3());
      this.method2("swords", new Attackindicator2Handler5(Bridge6_4::bridge$isItemSword));
      this.method2("axes", new Attackindicator2Handler5(Bridge6_4::bridge$isAxe));
      this.method2("enderPearl", new Attackindicator2Handler25(Bridge6_4::bridge$isEnderPearl));
      this.method2("chorusFruit", new Attackindicator2Handler25(Bridge6_4::bridge$isChorusFruit));
      this.method2("bows", new Attackindicator2Handler22());
      this.method3("tridents", new Attackindicator2Handler26(true, Bridge6_4::bridge$isTrident), 6);
      this.method3("spears", new Attackindicator2Handler262(), 35);
      this.method2("pickaxes", new Attackindicator2Handler5(Bridge6_4::bridge$isItemPickaxe));
      this.method2("shovel", new Attackindicator2Handler5(Bridge6_4::bridge$isItemShovel));
      this.method2("shields", new Attackindicator2Handler25(Bridge6_4::bridge$isShield));
      this.method2("consumables", new Attackindicator2Handler23());
      this.method3("windCharge", new Attackindicator2Handler25(Bridge6_4::bridge$isWindCharge), 22);
      this.method2("storage", new Attackindicator2Handler24());
      this.method2("nonWeapons", new Attackindicator2Handler4());
   }

   public void method1(RootSettingsAssembler.Data var1, boolean var2) {
      for (Entry var4 : this.field1.entrySet()) {
         Attackindicator2 var5 = (Attackindicator2)var4.getValue();
         if (var5.isVanilla() == var2) {
            ToggleOption var6 = (ToggleOption)var4.getKey();
            var1.method9(new ClientOption[]{var6});
         }
      }
   }

   private void method2(@Annotation(method1 = Annotation.Type.SETTING) String var1, Attackindicator2 var2) {
      this.field1
         .put(
            (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7(var1)
                  .method4(var2.isVanilla() && !(var2 instanceof Attackindicator2Handler4)))
               .method31(),
            var2
         );
   }

   private void method3(@Annotation(method1 = Annotation.Type.SETTING) String var1, Attackindicator2 var2, int var3) {
      if (ThreadModuleDump63.MC_VERSION >= var3) {
         this.field1
            .put(
               (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7(var1).method4(var2.isVanilla()))
                  .method31(),
               var2
            );
      } else {
         this.field2.add(var1);
      }
   }

   public void method4(@Nullable Bridge5Extension_5 var1, AttackIndicator var2) {
      Attackindicator2 var3 = this.field4 == null ? null : this.field4.field5;
      float var4 = this.field4 == null ? -1.0F : this.field4.getValue();
      this.field4 = null;
      if (var1 != null) {
         ArrayList var5 = new ArrayList();

         for (Entry var7 : this.field1.entrySet()) {
            ToggleOption var8 = (ToggleOption)var7.getKey();
            Attackindicator2 var9 = (Attackindicator2)var7.getValue();
            if (var8.get()) {
               var9.method8(var1);
               if (var9.method1(var1)) {
                  var5.add(var9);
               }
            } else {
               var9.method8(null);
            }
         }

         if (!var5.isEmpty()) {
            var5.sort(Comparator.comparingInt(Attackindicator2::method5).reversed());

            for (int var10 = 0; var10 < var5.size(); var10++) {
               Attackindicator2 var13 = (Attackindicator2)var5.get(var10);
               if (var13.method3(var1)) {
                  this.field4 = new Attackindicator3(var13, var1);
                  break;
               }
            }

            if (this.field4 == null) {
               for (int var11 = 0; var11 < var5.size(); var11++) {
                  Attackindicator2 var14 = (Attackindicator2)var5.get(var11);
                  if (var14.method4(var1)) {
                     this.field4 = new Attackindicator3(var14, var1);
                     break;
                  }
               }
            }

            if (this.field4 == null) {
               this.field4 = new Attackindicator3((Attackindicator2)var5.get(0), var1);
            }

            float var12 = this.field4.getValue();
            if (this.field4.field5 == var3 && var4 != -1.0F && var12 >= 1.0F && var4 < var12) {
               if (var3 instanceof Attackindicator2Handler4
                  || var3 instanceof Attackindicator2Handler
                  || var3 instanceof Attackindicator2Handler3
                  || var3 instanceof Attackindicator2Handler23
                  || var3 instanceof Attackindicator2Handler24) {
                  return;
               }

               if (var3.isVanilla() && var2.method14().get() || !var3.isVanilla() && var2.method15().get()) {
                  var3.method7(var1);
               }
            }
         }
      }
   }

   @Generated
   public Set<String> method5() {
      return this.field2;
   }

   @Generated
   public Attackindicator3 method6() {
      return this.field3;
   }

   @Nullable
   @Generated
   public Attackindicator3 method7() {
      return this.field4;
   }

   private static class Data implements Attackindicator2 {
      private final ItemStackBridge field4 = Bridge.method8().method38(Bridge.method28().method16());

      @Override
      public boolean method1(@NotNull Bridge5Extension_5 var1) {
         return true;
      }

      @Override
      public boolean method4(@NotNull Bridge5Extension_5 var1) {
         return true;
      }

      @Override
      public float method2(@NotNull Bridge5Extension_5 var1) {
         return 1.0F;
      }

      @Nullable
      @Override
      public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
         return this.field4;
      }
   }
}
