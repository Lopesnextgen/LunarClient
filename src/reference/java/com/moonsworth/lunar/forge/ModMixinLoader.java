package com.moonsworth.lunar.forge;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.loader.PipelineStage;
import java.util.ArrayList;
import java.util.List;

public abstract class ModMixinLoader extends Ichor5Handler_2 {
   protected List<String> field2;
   protected List<JsonObject> field3;
   protected boolean initialized = false;
   private final String field4;
   private final Ichor5Iterator field5;

   public ModMixinLoader(Ichor5Iterator ichor5iterator1, String text2, String text3, String text4, String text5) {
      super(text2, text3, PipelineStage.MIXIN);
      this.field5 = ichor5iterator1;
      this.field2 = new ArrayList<>();
      this.field3 = new ArrayList<>();
      this.field4 = text4;
      ichor5iterator1.method2(text2, text5);
   }

   public void loadIchor(IchorTransformer autocloseableiterator21) {
      this.field5.method3(autocloseableiterator21.method20(), this.key, this.field4);
      super.loadIchor(autocloseableiterator21);
   }

   public void method3(IchorTransformer autocloseableiterator21) {
   }

   public void method4(IchorTransformer autocloseableiterator21) {
      if (!this.initialized) {
         this.initialized = true;
         this.method3(autocloseableiterator21);
      }
   }

   public List<String> method1(IchorTransformer autocloseableiterator21) {
      this.method4(autocloseableiterator21);
      return this.field2;
   }

   public List<JsonObject> method2(IchorTransformer autocloseableiterator21) {
      this.method4(autocloseableiterator21);
      return this.field3;
   }
}
