package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class FatalIchorError13 {
   private static boolean field1 = false;
   private FatalIchorError13.Data[] field2;
   private ArrayList<FatalIchorError13.Data> field3;
   private final Object field4 = new Object();
   private Set<String> field5;
   private Set<String> field6;

   public FatalIchorError13(IchorPipeline var1) {
      this.field3 = new ArrayList<>(var1.method33().size());
   }

   public void method1(IchorPipeline var1, Ichor4 var2, FatalIchorError8 var3) {
      if (field1 && var3.method1()) {
         ClassNode var4 = var3.getClassNode();
         int var5 = var1.method33().indexOf(var2);
         if (var5 == 0) {
            String[] var6 = null;
            if (!var4.fields.isEmpty()) {
               this.field5 = Collections.newSetFromMap(new ConcurrentHashMap<>());
               var6 = new String[var4.fields.size()];

               for (int var7 = 0; var7 < var6.length; var7++) {
                  String var8 = (((FieldNode)var4.fields.get(var7)).name + ":" + ((FieldNode)var4.fields.get(var7)).desc).intern();
                  var6[var7] = var8;
                  this.field5.add(var8);
               }
            }

            String[] var18 = null;
            if (!var4.methods.isEmpty()) {
               this.field6 = Collections.newSetFromMap(new ConcurrentHashMap<>());
               var18 = new String[var4.methods.size()];

               for (int var20 = 0; var20 < var18.length; var20++) {
                  MethodNode var9 = (MethodNode)var4.methods.get(var20);
                  String var10 = (var9.name + var9.desc).intern();
                  var18[var20] = var10;
                  this.field6.add(var10);
               }
            }

            if (var6 != null || var18 != null) {
               synchronized (this.field4) {
                  this.field3.add(new FatalIchorError13.Data(var2, var6, var18));
               }
            }
         } else {
            if (this.field3 == null) {
               return;
            }

            ArrayList var17 = new ArrayList();
            ArrayList var19 = new ArrayList();

            for (FieldNode var25 : var4.fields) {
               String var28 = (var25.name + ":" + var25.desc).intern();
               if (this.field5 != null && this.field5.add(var28)) {
                  var17.add(var28);
               }
            }

            for (MethodNode var26 : var4.methods) {
               String var29 = (var26.name + var26.desc).intern();
               if (this.field6 != null && this.field6.add(var29)) {
                  var19.add(var29);
               }
            }

            boolean var24 = false;
            String[] var27 = null;
            if (!var17.isEmpty()) {
               var27 = var17.toArray(new String[0]);
               var24 = true;
            }

            String[] var30 = null;
            if (!var19.isEmpty()) {
               var30 = var19.toArray(new String[0]);
               var24 = true;
            }

            if (var24) {
               synchronized (this.field4) {
                  if (this.field3 != null) {
                     this.field3.add(new FatalIchorError13.Data(var2, var27, var30));
                  }
               }
            }

            if (var5 == var1.method33().size() - 1) {
               this.field6 = null;
               this.field5 = null;
               synchronized (this.field4) {
                  if (this.field3 != null) {
                     this.field2 = this.field3.toArray(new FatalIchorError13.Data[0]);
                     this.field3 = null;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public FatalIchorError13.Data method2(String var1) {
      if (this.field2 != null) {
         for (int var2 = this.field2.length - 1; var2 > 0; var2--) {
            FatalIchorError13.Data var3 = this.field2[var2];
            if (var3.field2 != null) {
               for (int var4 = var3.field2.length - 1; var4 >= 0; var4--) {
                  if (var3.field2[var4].startsWith(var1)) {
                     return var3;
                  }
               }
            }
         }
      }

      return null;
   }

   @Nullable
   public FatalIchorError13.Data method3(String var1) {
      if (this.field2 != null) {
         for (int var2 = this.field2.length - 1; var2 > 0; var2--) {
            FatalIchorError13.Data var3 = this.field2[var2];
            if (var3.field3 != null) {
               for (int var4 = var3.field3.length - 1; var4 >= 0; var4--) {
                  if (var3.field3[var4].startsWith(var1)) {
                     return var3;
                  }
               }
            }
         }
      }

      return null;
   }

   public class Data {
      private final Ichor4 field1;
      @Nullable
      private final String[] field2;
      @Nullable
      private final String[] field3;

      public Data(Ichor4 var1, @Nullable String[] var2, @Nullable String[] var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public Ichor4 method1() {
         return this.field1;
      }

      @Nullable
      public String[] fields() {
         return this.field2;
      }

      @Nullable
      public String[] methods() {
         return this.field3;
      }
   }
}
