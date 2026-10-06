package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Iterator2 extends Fishing2Iterator {
   private final Framework7Extension field10;
   private final boolean field11;
   private final Supplier<List<Fishing2Iterator>> field12;
   private final Supplier<List<Fishing2Loader<?, ?>>> field13;

   public Fishing2Iterator2(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      Framework7Extension var2,
      Supplier<List<Fishing2Iterator>> var3,
      Supplier<List<Fishing2Loader<?, ?>>> var4
   ) {
      super(var1);
      this.field10 = var2;
      this.field11 = var2.isEnabled();
      this.field12 = var3;
      this.field13 = var4;
      ((List)var3.get()).forEach(this::method2);
      ((List)var4.get()).forEach(this::method3);
   }

   public Fishing2Iterator method2(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Fishing2Iterator2 var4 = new Fishing2Iterator2(var1, this.field10, this.field12, this.field13);
      return this.method6(var4, var1, var2, var3);
   }

   @Override
   public void method6(ThreadModuleDump6<Nameplate4> var1, int var2) {
      if (this.field10.isEnabled() != this.isEnabled()) {
         for (Fishing2Loader var4 : super.field2.values()) {
            var4.method3();
         }
      }

      ModEnabledState var5 = (ModEnabledState)this.field10.method1(Framework.field6);
      if (var5 != null) {
         var5.setEnabled(this.isEnabled());
      }

      super.method6(var1, var2);
   }

   @Override
   public void method7() {
      ModEnabledState var1 = (ModEnabledState)this.field10.method1(Framework.field6);
      if (var1 != null) {
         var1.setEnabled(this.field11);
      }

      super.method7();
   }

   @Override
   public String type() {
      return this.field10.getId();
   }

   @Override
   public List<String> method10() {
      return List.of("gameplay", "effect");
   }

   @Generated
   public Framework7Extension getFeature() {
      return this.field10;
   }
}
