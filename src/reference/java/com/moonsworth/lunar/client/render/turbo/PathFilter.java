package com.moonsworth.lunar.client.render.turbo;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.inactive.Inactive;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Handler;
import com.moonsworth.lunar.client.inactive.mixin.Gui2Impl;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveException;
import com.moonsworth.lunar.client.cosmetics.gecko.InactiveTask;
import com.moonsworth.lunar.client.cosmetics.gecko.CombinedTask;
import com.moonsworth.lunar.client.cosmetics.gecko.AnimationTaskEntry;
import com.moonsworth.lunar.client.cosmetics.molang.EvaluatorImpl;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class PathFilter implements TurboEntityFilter<EmoteDefinition> {
   private final RenderContext field1;
   private final TurboPathFollower field2;
   private final Object2LongMap<AnimationTaskEntry> field3;
   private final Map<String, Object> field4 = new HashMap<>(1);
   private @Nullable InactiveTask field5;
   private int field6;

   public PathFilter(RenderContext var1, EmoteDefinition var2, Gui2Impl var3) {
      this.field1 = var1;
      this.field2 = this.method1(var2, var3);
      this.field3 = new Object2LongOpenHashMap(var3.method23().length);
      this.field3.defaultReturnValue(0L);
   }

   @NotNull
   protected TurboPathFollower method1(EmoteDefinition var1, Gui2Impl var2) {
      try {
         Inactive var3 = var2.method20();

         TurboPathFollower var4 = switch (var3.method12()) {
            case GROUND -> new PathFollower(var1, ThreadModuleDump63.method8());
            case FLYING -> new TickPathFollower(var1, ThreadModuleDump63.method8());
            default -> throw new InactiveException("Geckolib unknown pathfinder: " + var3.method12().name() + " - " + var1.method28().getName());
         };
         var4.method23(var3.method10());
         return var4;
      } catch (Throwable var5) {
         throw var5;
      }
   }

   public boolean method2(TurboEntityManager var1, Itemcounter6Extension var2, Bridge5Extension_5 var3, EmoteDefinition var4) {
      Gui2Handler var5 = var4.method28().method6().orElse(null);
      if (!(var5 instanceof Gui2Impl var6)) {
         return true;
      } else {
         var4.method44(var4.method43() + 1);
         if (this.field5 == null) {
            this.method3(var4, var2, var6);
         }

         EvaluatorImpl var7 = var4.method30().method9();
         Inactive var8 = var4.method31();
         var4.method41(var8.method7().evaluate(var7));
         var4.method36((float)var8.method8().evaluate(var7));
         var4.method3(this);
         if (this.field6 > 0) {
            this.field6--;
            if (this.field6 <= 0) {
               this.field5 = null;
            }

            return false;
         } else {
            if (this.field5 != null && (this.field5.method4() == 0 || var2.bridge$getGameTime() % this.field5.method4() == 0L)) {
               this.field5.method6(var4, this);
               if (this.field5.method1(var4, this) || this.field5.isCancellable()) {
                  this.field5.method3(var4, this);
                  this.field6 = this.field5.getDuration();
                  if (this.field6 <= 0) {
                     this.field5 = null;
                  }
               }
            }

            return false;
         }
      }
   }

   private void method3(EmoteDefinition var1, Itemcounter6Extension var2, Gui2Impl var3) {
      AnimationTaskEntry var4 = var1.method45();
      long var5 = var2.bridge$getGameTime();
      if (var4 != null) {
         this.field3.put(var4, var5 + var4.method4());
      }

      var1.method44(0);
      var1.method46(null);
      Evaluator var7 = this.field1.getEvaluator();

      for (AnimationTaskEntry var11 : var3.method23()) {
         if (var4 != var11 && this.field3.getLong(var11) <= var5 && var11.method2() != null && var11.method1().evaluate(var7) == 1.0) {
            var1.method46(var11);
            var1.method48(var11);
            this.field5 = null;
            InactiveTask[] var12 = var11.method2();
            if (var12.length == 1) {
               InactiveTask var13 = var12[0];
               if (var13 != null && var13.method2(var1, this)) {
                  this.field5 = var13;
               }
            } else if (var12.length > 1) {
               CombinedTask var14 = new CombinedTask(var12);
               if (var14.method2(var1, this)) {
                  this.field5 = var14;
               }
            }
            break;
         }
      }
   }

   @Generated
   public RenderContext method4() {
      return this.field1;
   }

   @Generated
   public TurboPathFollower method5() {
      return this.field2;
   }

   @Generated
   public Map<String, Object> method6() {
      return this.field4;
   }
}
