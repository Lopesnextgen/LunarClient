package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.LinkedHashMapImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public abstract class Fishing2Iterator implements Fishing2<Integer, Fishing2Iterator> {
   protected LinkedHashMapImpl<String, Fishing2Iterator> field1;
   protected LinkedHashMapImpl<String, Fishing2Loader<?, ?>> field2;
   private boolean field3 = true;
   private boolean enabled = true;
   private boolean field4 = false;
   private boolean field5 = true;
   private boolean field6 = true;
   private Fishing2Iterator.Type field7 = Fishing2Iterator.Type.NONE;
   private boolean field8 = false;
   private boolean field9 = false;

   public Fishing2Iterator(com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var1) {
      this.field1 = new LinkedHashMapImpl<>(var1);
      this.field2 = new LinkedHashMapImpl<>(var1);
      this.field1.method5(this::method4);
      this.field2.method5(this::method5);
   }

   public Fishing2Iterator method1() {
      this.field3 = false;
      this.enabled = false;
      return this;
   }

   protected void method2(Fishing2Iterator var1) {
      this.field1.put(var1.type(), var1);
   }

   protected void method3(Fishing2Loader<?, ?> var1) {
      this.field2.put(var1.type(), var1);
   }

   protected void method4(String var1) {
      Fishing2Iterator var2 = this.method11().get(var1);
      var2.method7();
   }

   protected void method5(String var1) {
   }

   public void method6(ThreadModuleDump6<Nameplate4> var1, int var2) {
      if (this.enabled) {
         ((Nameplate4)var1.get()).method9().method1(this);

         for (Fishing2Iterator var4 : this.field1.values()) {
            var4.method6(var1, var2);
         }

         for (Fishing2Loader var6 : this.field2.values()) {
            var6.method12(var1, var2);
         }
      }
   }

   public void method7() {
      for (Fishing2Loader var2 : this.field2.values()) {
         var2.method3();
      }
   }

   public Fishing2Iterator method8(
      Fishing2Iterator var1, com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var2, Range<Integer> var3, Range<Integer> var4
   ) {
      LinkedHashMapImpl var5 = new LinkedHashMapImpl(var2);

      for (Entry var7 : this.field1.entrySet()) {
         var5.put((String)var7.getKey(), (Fishing2Iterator)((Fishing2Iterator)var7.getValue()).method1(var2, var3, var4));
      }

      var1.field1 = var5;
      LinkedHashMapImpl var9 = new LinkedHashMapImpl(var2);

      for (Entry var8 : this.field2.entrySet()) {
         var9.put((String)var8.getKey(), (Fishing2Loader)((Fishing2Loader)var8.getValue()).method7(var2, var3, var4));
      }

      var1.field2 = var9;
      var1.field3 = this.field3;
      var1.enabled = this.enabled;
      var1.field4 = this.field4;
      var1.field5 = this.field5;
      var1.field6 = this.field6;
      if (var1 instanceof Fishing2Iterator3 var11) {
         var11.setType(var1.type());
      }

      return var1;
   }

   public String method9() {
      return Client.method109().method67().method2("rewind", this.type(), new Object[0]);
   }

   public abstract String type();

   public abstract List<String> method10();

   @Generated
   public LinkedHashMapImpl<String, Fishing2Iterator> method11() {
      return this.field1;
   }

   @Generated
   public LinkedHashMapImpl<String, Fishing2Loader<?, ?>> method12() {
      return this.field2;
   }

   @Generated
   public boolean method13() {
      return this.field3;
   }

   @Generated
   public boolean isEnabled() {
      return this.enabled;
   }

   @Generated
   public boolean isExtended() {
      return this.field4;
   }

   @Generated
   public boolean method14() {
      return this.field5;
   }

   @Generated
   public boolean method15() {
      return this.field6;
   }

   @Generated
   public Fishing2Iterator.Type method16() {
      return this.field7;
   }

   @Generated
   public boolean method17() {
      return this.field8;
   }

   @Generated
   public boolean method18() {
      return this.field9;
   }

   @Generated
   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   @Generated
   public void method19(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public void method20(boolean var1) {
      this.field5 = var1;
   }

   @Generated
   public void method21(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public void method22(Fishing2Iterator.Type var1) {
      this.field7 = var1;
   }

   @Generated
   public void method23(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public void method24(boolean var1) {
      this.field9 = var1;
   }

   public enum Type {
      NONE,
      KEYFRAMES,
      CHILD_PROPERTIES;
   }
}
