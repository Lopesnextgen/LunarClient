package com.moonsworth.lunar.client.framework.mod;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableSet.Builder;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.calculator.mixin.Calculator;
import com.moonsworth.lunar.client.framework.metadata.ModMetadataFetcher;
import com.moonsworth.lunar.client.framework.ModMetadata;
import com.moonsworth.lunar.client.framework.mod.Nameplate2;
import com.moonsworth.lunar.client.framework.mod.Nameplate5;
import com.moonsworth.lunar.client.util.Annotation5;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public interface ModDetails {
   String getName();

   String getDescription();

   Set<Calculator2Handler> method1();

   Set<String> method2();

   Set<String> method3();

   boolean isVanilla();

   boolean method4();

   String method5(String var1, Object... var2);

   Calculator method6(String var1);

   static ModDetails.Data method7() {
      return new ModDetails.Data();
   }

   class Data {
      @Nullable
      private String field1;
      @Nullable
      private ImmutableSet<Calculator2Handler> field2;
      @Nullable
      private Set<String> field3;
      @Nullable
      private Set<String> field4;
      @Nullable
      private Supplier<String> field5;
      @Nullable
      private Supplier<String> field6;
      private boolean field7;
      private boolean field8;
      private boolean field9;
      private boolean field10 = true;

      public ModDetails.Data method1(Calculator2Handler... var1) {
         this.field2 = ImmutableSet.copyOf(var1);
         return this;
      }

      public ModDetails.Data method2(String... var1) {
         this.field3 = Set.of(var1);
         return this;
      }

      public ModDetails.Data method3(String... var1) {
         this.field4 = Set.of(var1);
         return this;
      }

      public ModDetails.Data method4(Supplier<String> var1) {
         this.field5 = var1;
         return this;
      }

      public ModDetails.Data method5(Supplier<String> var1) {
         this.field6 = var1;
         return this;
      }

      public ModDetails.Data method6(@Annotation5 String var1) {
         this.field1 = var1;
         return this;
      }

      public ModDetails.Data method7() {
         this.field7 = true;
         return this;
      }

      public ModDetails.Data method8() {
         this.field8 = true;
         return this;
      }

      public ModDetails.Data method9(boolean var1) {
         this.field9 = var1;
         return this;
      }

      public ModDetails.Data method10() {
         this.field10 = false;
         return this;
      }

      public ModDetails method11(Framework7Extension var1) {
         String var2 = this.field1 == null ? var1.getId() : this.field1;
         ModMetadata var3 = null;
         if (!var1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field16)) {
            ModMetadataFetcher var4 = Client.method109().method38();
            if (var4 != null) {
               var3 = var4.method1(var2);
            }
         }

         Builder var11 = ImmutableSet.builder();
         boolean var5 = ModMetadataFetcher.field2 != null;
         if (var5 && this.field8) {
            var11.add(Calculator2Handler.field7);
         }

         ModSupport var6 = (ModSupport)var1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field18);
         if (this.field7 || var6 != null && !var6.method1()) {
            boolean var12 = false;
            if (this.field2 != null) {
               UnmodifiableIterator var8 = this.field2.iterator();

               while (var8.hasNext()) {
                  Calculator2Handler var9 = (Calculator2Handler)var8.next();
                  if (var9 == Calculator2Handler.field7) {
                     var12 = true;
                     break;
                  }
               }
            }

            if (var12) {
               var11.add(Calculator2Handler.field7);
            }
         } else {
            Object var7 = new HashSet();
            if (this.field2 != null) {
               var7.addAll(this.field2);
            }

            if (var1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(Framework.field1)) {
               var7.add(Calculator2Handler.field4);
            }

            if (var3 != null) {
               var7 = var3.method2((Set<Calculator2Handler>)var7);
            }

            var11.addAll((Iterable)var7);
         }

         ImmutableSet var13;
         ImmutableSet var14;
         String var15;
         if (var3 != null) {
            var13 = var3.method3(this.field3);
            var14 = var3.method4(this.field4);
            var15 = var3.method1(var2);
         } else {
            var13 = this.field3 != null ? ImmutableSet.copyOf(this.field3) : ImmutableSet.of();
            var14 = this.field4 != null ? ImmutableSet.copyOf(this.field4) : ImmutableSet.of();
            var15 = "features." + var2;
         }

         Nameplate5 var10;
         if (this.field5 == null && this.field6 == null) {
            var10 = new Nameplate5(var15, var11.build(), var13, var14, this.field9, this.field10);
         } else {
            var10 = new Nameplate2(var15, var11.build(), var13, var14, this.field9, this.field10, this.field5, this.field6);
         }

         return var10;
      }
   }
}
