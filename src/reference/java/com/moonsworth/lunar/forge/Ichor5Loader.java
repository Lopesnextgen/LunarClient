package com.moonsworth.lunar.forge;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.util.ArrayList;
import java.util.List;

public abstract class Ichor5Loader extends Ichor5Handler_2 {
   protected List<String> field2;
   protected List<JsonObject> field3;
   protected boolean initialized = false;
   private final String field4;
   private final Ichor5Iterator field5;

   public Ichor5Loader(Ichor5Iterator var1, String text, String text2, String text3, String text4) {
      super(text, text2, Ichor4Type.MIXIN);
      this.field5 = var1;
      this.field2 = new ArrayList<>();
      this.field3 = new ArrayList<>();
      this.field4 = text3;
      var1.method2(text, text4);
   }

   @Override
   public void loadIchor(IchorTransformer var1) {
      this.field5.method3(var1.method20(), this.key, this.field4);
      super.loadIchor(var1);
   }

   public void method3(IchorTransformer var1) {
   }

   public void method4(IchorTransformer var1) {
      if (!this.initialized) {
         this.initialized = true;
         this.method3(var1);
      }
   }

   @Override
   public List<String> method1(IchorTransformer var1) {
      this.method4(var1);
      return this.field2;
   }

   @Override
   public List<JsonObject> method2(IchorTransformer var1) {
      this.method4(var1);
      return this.field3;
   }
}
