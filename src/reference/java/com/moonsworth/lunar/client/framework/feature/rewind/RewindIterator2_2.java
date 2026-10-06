package com.moonsworth.lunar.client.framework.feature.rewind;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.Annotation7;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import lombok.Generated;
import org.apache.commons.lang3.Range;

@Annotation7
public abstract class RewindIterator2_2<T extends RewindIterator2_2<T>> extends RewindIterator<T> {
   @SerializedName("contentStart")
   private int field7;

   public RewindIterator2_2(Nameplate2 var1) {
      super(var1);
   }

   protected T method1(T var1, Nameplate2 var2, Range<Integer> var3, Range<Integer> var4) {
      var1.method9(this.method8() + (Integer)var4.getMinimum() - (Integer)var3.getMinimum());
      return super.method3((T)var1, var2, var3, var4);
   }

   @Override
   public void method2(ThreadModuleDump6<Nameplate4> var1, Range<Integer> var2, int var3) {
      RewindHandlers var4 = ((Nameplate4)var1.get()).method6();
      Highlight_3 var5 = ((Nameplate4)var1.get()).method4();
      double var6 = var5.method9();
      int var8 = var3 - (Integer)var2.getMinimum();
      double var9 = this.ICRHORIIHOHROHOHOCOOHOOCOORRHO(Range.between(0, var8));
      double var11 = this.field7 + var9 - this.method12(var8, false);
      long var13 = (long)(var11 * var6) + 100L;
      long var15 = var13 - this.method3((Nameplate4)var1.get());
      if (!var4.method41().method5() && var15 != 0L) {
         this.method4(var5, var4, var13, var15);
      }

      super.method2(var1, var2, var3);
   }

   protected abstract long method3(Nameplate4 var1);

   protected abstract void method4(Highlight_3 var1, RewindHandlers var2, long var3, long var5);

   public abstract long method5(Rewind2_3 var1);

   @Override
   public JsonElement method16(Highlight_3 var1, Range<Integer> var2, RewindHandlers var3, Runnable var4) {
      JsonObject var5 = super.method16(var1, var2, var3, var4).getAsJsonObject();
      var5.addProperty("trimLeft", this.field7);
      long var6 = this.method5(var3.method40());
      int var8 = (int)(var6 / var1.method9());
      int var9 = var8 - ((Integer)var2.getMaximum() - (Integer)var2.getMinimum()) - this.field7;
      var5.addProperty("trimRight", var9);
      return var5;
   }

   @Generated
   public int method8() {
      return this.field7;
   }

   @Generated
   public void method9(int var1) {
      this.field7 = var1;
   }
}
