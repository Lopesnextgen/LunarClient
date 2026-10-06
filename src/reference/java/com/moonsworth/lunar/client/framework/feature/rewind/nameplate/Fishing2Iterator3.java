package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public class Fishing2Iterator3 extends Fishing2Iterator {
   private final List<String> field10;
   private String type;
   private final Supplier<List<Fishing2Loader<?, ?>>> field11;
   private final ToggleOption field12;

   public Fishing2Iterator3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      Supplier<List<Fishing2Loader<?, ?>>> var4
   ) {
      this(var1, var2, var3, var4, null);
   }

   public Fishing2Iterator3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      Supplier<List<Fishing2Loader<?, ?>>> var4,
      ToggleOption var5
   ) {
      this(var1, var2, var3, var4, true, true, var5);
   }

   public Fishing2Iterator3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1,
      List<String> var2,
      String var3,
      Supplier<List<Fishing2Loader<?, ?>>> var4,
      boolean var5,
      boolean var6,
      ToggleOption var7
   ) {
      super(var1);
      this.field10 = var2;
      this.type = var3;
      this.field11 = var4;
      this.method20(var5);
      this.method21(var6);
      this.field12 = var7;
      ((List)var4.get()).forEach(this::method3);
   }

   @Override
   public void method6(ThreadModuleDump6<Nameplate4> var1, int var2) {
      if (this.field12 != null && (Boolean)this.field12.get() != this.isEnabled()) {
         this.field12.method10(this.isEnabled());
      }

      super.method6(var1, var2);
   }

   @Override
   public void method7() {
      if (this.field12 != null) {
         this.field12.method10(this.field12.method8());
      }

      super.method7();
   }

   public Fishing2Iterator method3(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1, Range<Integer> var2, Range<Integer> var3
   ) {
      Fishing2Iterator3 var4 = new Fishing2Iterator3(
         var1, this.field10, this.type, this.field11, this.method14(), this.method15(), this.field12
      );
      return this.method6(var4, var1, var2, var3);
   }

   @Override
   public String type() {
      return this.type;
   }

   @Override
   public List<String> method10() {
      return this.field10;
   }

   @Generated
   public void setType(String var1) {
      this.type = var1;
   }
}
