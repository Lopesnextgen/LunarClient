package com.moonsworth.lunar.client.config.option.trait;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import com.moonsworth.lunar.client.config.option.trait.TraitDebugFormatter;
import com.moonsworth.lunar.client.config.option.trait.MutableTraitHost;
import com.moonsworth.lunar.client.config.option.trait.TraitSnapshot;

public class DebugTraitContainer extends MutableTraitContainer {
   @Nullable
   private final String field5;
   private boolean field6 = false;

   public DebugTraitContainer(Lightoverlay var1) {
      super(var1);
      this.field5 = null;
   }

   DebugTraitContainer(Lightoverlay var1, Int2ObjectMap<Object> var2) {
      super(var1, var2);
      this.field5 = null;
   }

   public DebugTraitContainer(Lightoverlay var1, @Nullable String var2) {
      super(var1);
      this.field5 = var2;
   }

   DebugTraitContainer(Lightoverlay var1, @Nullable String var2, Int2ObjectMap<Object> var3) {
      super(var1, var3);
      this.field5 = var2;
   }

   public DebugTraitContainer method1(boolean var1) {
      this.field6 = var1;
      return this;
   }

   public String method4() {
      return TraitDebugFormatter.method1(this);
   }

   public List<String> method5() {
      List var1 = TraitDebugFormatter.method3(this);
      if (!var1.isEmpty()) {
         this.method15("Validation found " + var1.size() + " errors:");

         for (String var3 : var1) {
            this.method15("  - " + var3);
         }
      }

      return var1;
   }

   public String method5(TraitType<?> var1) {
      return TraitDebugFormatter.method2(this.method1(), var1);
   }

   public String method6(TraitType<?> var1) {
      return TraitDebugFormatter.method4(this.method1(), var1);
   }

   public String method6(TraitContainer var1) {
      return TraitDebugFormatter.method5(this, var1);
   }

   @Nullable
   @Override
   public <T> T method1(TraitType<T> var1, @Nullable T var2) {
      Object var3 = super.method1(var1, var2);
      if (var3 != var2) {
         String var4 = this.method1().method2(var1.getId());
         this.method14("Set trait " + var4 + " (" + var1.getId() + ") to " + (var2 != null ? var2 : "null") + " - Previous value was: " + var3);
      }

      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T set(int var1, @Nullable T var2) {
      Object var3 = super.set(var1, var2);
      if (var3 != var2) {
         String var4 = this.method1().method2(var1);
         this.method14("Set trait " + var4 + " (" + var1 + ") to " + (var2 != null ? var2 : "null") + " - Previous value was: " + var3);
      }

      return (T)var3;
   }

   @Nullable
   @Override
   public <T> T method4(TraitType<T> var1, Function<TraitType<T>, ? extends @NotNull T> var2) {
      Object var3 = var2.apply(var1);
      boolean var4 = this.method2(var1);
      Object var5 = super.method4(var1, var1x -> (T)var3);
      if (var4 && var5 != var3) {
         String var6 = this.method1().method2(var1.getId());
         this.method14("Computed present trait " + var6 + " (" + var1.getId() + ") to " + var3 + " - Previous value was: " + var5);
      }

      return (T)var5;
   }

   @Override
   public <T> T method5(TraitType<T> var1, Function<TraitType<T>, ? extends @NotNull T> var2) {
      Object var3 = var2.apply(var1);
      boolean var4 = this.method2(var1);
      Object var5 = super.method5(var1, var1x -> (T)var3);
      if (!var4) {
         String var6 = this.method1().method2(var1.getId());
         this.method14("Computed absent trait " + var6 + " (" + var1.getId() + ") to " + var3);
      }

      return (T)var5;
   }

   @Nullable
   @Override
   public <T> T method7(TraitType<? extends T> var1) {
      Object var2 = super.method7(var1);
      if (var2 != null) {
         String var3 = this.method1().method2(var1.getId());
         this.method14("Removed trait " + var3 + " (" + var1.getId() + ") - Previous value was: " + var2);
      }

      return (T)var2;
   }

   @Override
   public void method8(TraitSnapshot var1) {
      this.method14("Applying tweaks to container");
      super.method8(var1);
   }

   @Override
   public void method9(TraitContainer var1) {
      this.method14("Setting all traits from another container");
      super.method9(var1);
   }

   @Override
   public MutableTraitHost method3() {
      this.method14("Creating a copy of this container");
      MutableTraitContainer var1 = (MutableTraitContainer)super.method3();
      DebugTraitContainer var2 = new DebugTraitContainer(this.method1(), this.field5 == null ? null : this.field5 + "Copy");
      var2.method9(var1);
      var2.method1(this.field6);
      return var2;
   }

   private void method14(String var1) {
      if (this.field6) {
         if (this.field5 != null) {
            System.out.println("[DEBUG_TRAIT] [" + this.field5 + "] INFO: " + var1);
         } else {
            System.out.println("[DEBUG_TRAIT] INFO: " + var1);
         }
      }
   }

   private void method15(String var1) {
      if (this.field6) {
         if (this.field5 != null) {
            System.out.println("[DEBUG_TRAIT] [" + this.field5 + "] WARNING: " + var1);
         } else {
            System.out.println("[DEBUG_TRAIT] WARNING: " + var1);
         }
      }
   }
}
