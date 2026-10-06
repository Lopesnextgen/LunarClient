package com.moonsworth.lunar.ichor;

import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class Ichor5Handler_2 implements Ichor5 {
   protected String key;
   protected String version;
   protected Ichor4 field1;

   public Ichor5Handler_2(String var1, String var2, Ichor4 ichor4) {
      this.key = var1;
      this.version = var2;
      this.field1 = ichor4;
   }

   public String getKey() {
      return this.key;
   }

   public String getId() {
      return this.getKey();
   }

   @Nullable
   public String getVersion() {
      return this.version;
   }

   protected List<String> method1(IchorTransformer var1) {
      return List.of();
   }

   public List<JsonObject> method2(IchorTransformer var1) {
      return List.of();
   }

   public Optional<Path> method3(IchorPipeline var1) {
      return var1.method11(this.getKey());
   }

   protected void method4(MixinInternal2 var1, IchorPipeline var2) {
   }

   @Override
   public void loadIchor(IchorTransformer var1) {
      var1.method22().add(new Ichor5Handler$Data(this));
   }

   public boolean method5() {
      return false;
   }
}
