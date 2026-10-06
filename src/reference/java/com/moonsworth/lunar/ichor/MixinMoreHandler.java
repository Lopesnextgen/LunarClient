package com.moonsworth.lunar.ichor;

import java.io.InputStream;
import java.net.URL;
import org.cadixdev.bombe.provider.ClassProvider;

public class MixinMoreHandler implements MixinMore, Ichor5, ClassProvider {
   private static MixinMoreHandler field1 = null;
   private final MixinMoreHandler.Data field2;

   private MixinMoreHandler(ClassLoader var1) {
      this.field2 = new MixinMoreHandler.Data(new URL[0], var1);
   }

   @Override
   public void loadIchor(IchorTransformer var1) {
   }

   public byte[] get(String var1) {
      try {
         String var2 = var1.concat(".class");

         try (InputStream var3 = this.field2.getResourceAsStream(var2)) {
            return var3 == null ? null : var3.readAllBytes();
         }
      } catch (Exception var8) {
         return null;
      }
   }

   @Override
   public InputStream getResourceAsStream(String var1) {
      return this.field2.getResourceAsStream(var1);
   }

   @Override
   public URL findResource(String var1) {
      return this.field2.findResource(var1);
   }

   public static void method1(ClassLoader type, IchorPipeline var1, URL var2) {
      if (field1 == null) {
         field1 = new MixinMoreHandler(type);
         var1.method1(field1);
      }

      field1.field2.addURL(var2);
   }

   private static class Data extends java.net.URLClassLoader {
      private Data(URL[] var1, ClassLoader var2) {
         super(var1, var2);
      }

      @Override
      public void addURL(URL var1) {
         super.addURL(var1);
      }
   }
}
