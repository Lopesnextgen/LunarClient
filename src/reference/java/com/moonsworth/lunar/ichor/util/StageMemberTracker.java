package com.moonsworth.lunar.ichor.util;

import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class StageMemberTracker {
   private static boolean field1 = false;
   private StageMemberTracker.Data[] field2;
   private ArrayList<StageMemberTracker.Data> field3;
   private final Object field4 = new Object();
   private Set<String> field5;
   private Set<String> field6;

   public StageMemberTracker(IchorPipeline ichor71) {
      this.field3 = new ArrayList<>(ichor71.method33().size());
   }

   public void method1(IchorPipeline ichor71, IchorStage ichor42, ClassEntry fatalichorerror83) {
      if (field1 && fatalichorerror83.method1()) {
         ClassNode node4 = fatalichorerror83.getClassNode();
         int number5 = ichor71.method33().indexOf(ichor42);
         if (number5 == 0) {
            String[] items6 = null;
            if (!node4.fields.isEmpty()) {
               this.field5 = Collections.newSetFromMap(new ConcurrentHashMap<>());
               items6 = new String[node4.fields.size()];

               for (int index7 = 0; index7 < items6.length; index7++) {
                  String text8 = (((FieldNode)node4.fields.get(index7)).name + ":" + ((FieldNode)node4.fields.get(index7)).desc).intern();
                  items6[index7] = text8;
                  this.field5.add(text8);
               }
            }

            String[] items18 = null;
            if (!node4.methods.isEmpty()) {
               this.field6 = Collections.newSetFromMap(new ConcurrentHashMap<>());
               items18 = new String[node4.methods.size()];

               for (int index20 = 0; index20 < items18.length; index20++) {
                  MethodNode method9 = (MethodNode)node4.methods.get(index20);
                  String text10 = (method9.name + method9.desc).intern();
                  items18[index20] = text10;
                  this.field6.add(text10);
               }
            }

            if (items6 != null || items18 != null) {
               synchronized (this.field4) {
                  this.field3.add(new StageMemberTracker.Data(ichor42, items6, items18));
               }
            }
         } else {
            if (this.field3 == null) {
               return;
            }

            ArrayList list17 = new ArrayList();
            ArrayList list19 = new ArrayList();

            for (FieldNode field25 : node4.fields) {
               String text28 = (field25.name + ":" + field25.desc).intern();
               if (this.field5 != null && this.field5.add(text28)) {
                  list17.add(text28);
               }
            }

            for (MethodNode method26 : node4.methods) {
               String text29 = (method26.name + method26.desc).intern();
               if (this.field6 != null && this.field6.add(text29)) {
                  list19.add(text29);
               }
            }

            boolean flag24 = false;
            String[] items27 = null;
            if (!list17.isEmpty()) {
               items27 = list17.toArray(new String[0]);
               flag24 = true;
            }

            String[] items30 = null;
            if (!list19.isEmpty()) {
               items30 = list19.toArray(new String[0]);
               flag24 = true;
            }

            if (flag24) {
               synchronized (this.field4) {
                  if (this.field3 != null) {
                     this.field3.add(new StageMemberTracker.Data(ichor42, items27, items30));
                  }
               }
            }

            if (number5 == ichor71.method33().size() - 1) {
               this.field6 = null;
               this.field5 = null;
               synchronized (this.field4) {
                  if (this.field3 != null) {
                     this.field2 = this.field3.toArray(new StageMemberTracker.Data[0]);
                     this.field3 = null;
                  }
               }
            }
         }
      }
   }

   @Nullable
   public StageMemberTracker.Data method2(String text1) {
      if (this.field2 != null) {
         for (int index2 = this.field2.length - 1; index2 > 0; index2--) {
            StageMemberTracker.Data data3 = this.field2[index2];
            if (data3.field2 != null) {
               for (int index4 = data3.field2.length - 1; index4 >= 0; index4--) {
                  if (data3.field2[index4].startsWith(text1)) {
                     return data3;
                  }
               }
            }
         }
      }

      return null;
   }

   @Nullable
   public StageMemberTracker.Data method3(String text1) {
      if (this.field2 != null) {
         for (int index2 = this.field2.length - 1; index2 > 0; index2--) {
            StageMemberTracker.Data data3 = this.field2[index2];
            if (data3.field3 != null) {
               for (int index4 = data3.field3.length - 1; index4 >= 0; index4--) {
                  if (data3.field3[index4].startsWith(text1)) {
                     return data3;
                  }
               }
            }
         }
      }

      return null;
   }

   public class Data {
      private final IchorStage field1;
      @Nullable
      private final String[] field2;
      @Nullable
      private final String[] field3;

      public Data(IchorStage ichor41, @Nullable String[] items2, @Nullable String[] items3) {
         this.field1 = ichor41;
         this.field2 = items2;
         this.field3 = items3;
      }

      public IchorStage method1() {
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
