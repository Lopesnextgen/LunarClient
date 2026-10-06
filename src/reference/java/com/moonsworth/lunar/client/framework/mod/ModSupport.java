package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher;
import com.moonsworth.lunar.client.framework.mod.Nameplate11;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump43;
import com.moonsworth.lunar.client.util.ThreadModuleDump44;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public interface ModSupport {
   boolean method1();

   boolean method2();

   boolean method3(KeystrokesType var1);

   boolean method4();

   void setFlipped(boolean var1);

   static ModSupport.Data method5() {
      return new ModSupport.Data();
   }

   class Data implements ThreadModuleDump43<ModSupport.Data>, ThreadModuleDump44<ModSupport> {
      private boolean field1;
      private boolean field2 = true;
      @Nullable
      private String[] field3 = null;
      @Nullable
      private KeystrokesType field4 = null;

      public ModSupport.Data method1(int... var1) {
         int var2 = Bridge.getMinecraftVersion().getOrdinal();

         for (int var6 : var1) {
            if (var6 == var2) {
               this.field2 = true;
               return this;
            }
         }

         this.field2 = false;
         return this;
      }

      public ModSupport.Data method2(int... var1) {
         int var2 = Bridge.getMinecraftVersion().getOrdinal();

         for (int var6 : var1) {
            if (var6 == var2) {
               this.field2 = false;
               return this;
            }
         }

         this.field2 = true;
         return this;
      }

      public ModSupport.Data method3(String... var1) {
         if (this.field3 != null) {
            throw new UnsupportedOperationException("You are only allowed to set modules once!");
         }

         this.field1 = false;
         this.field3 = var1;
         return this;
      }

      public ModSupport.Data method4(String... var1) {
         if (this.field3 != null) {
            throw new UnsupportedOperationException("You are only allowed to set modules once!");
         }

         this.field1 = true;
         this.field3 = var1;
         return this;
      }

      public ModSupport.Data method5(KeystrokesType var1) {
         if (this.field4 != null) {
            throw new UnsupportedOperationException("You are only allowed to set a single server!");
         }

         this.field4 = var1;
         return this;
      }

      public ModSupport method6() {
         if (ModMetadataFetcher.field2 != null) {
            return Nameplate11.method4(true, null);
         }

         if (ThreadModuleDump63.field1) {
            return Nameplate11.method4(true, this.field4);
         }

         boolean var1 = this.field2;
         if (var1 && this.field3 != null && this.field3.length > 0) {
            boolean var2 = false;

            for (String var6 : this.field3) {
               if (this.field1 == ThreadModuleDump63.hasModule(var6)) {
                  var2 = true;
                  break;
               }
            }

            var1 = var2;
         }

         return Nameplate11.method4(var1, this.field4);
      }
   }
}
