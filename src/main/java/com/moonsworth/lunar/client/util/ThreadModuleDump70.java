package com.moonsworth.lunar.client.util;

public class ThreadModuleDump70 {
   private final int left;
   private final int top;
   private final int right;
   private final int bottom;

   public ThreadModuleDump70(int var1, int var2, int var3, int var4) {
      if (var1 <= var3 && var2 <= var4) {
         this.left = var1;
         this.top = var2;
         this.right = var3;
         this.bottom = var4;
      } else {
         throw new IllegalArgumentException("Invalid rectangle dimensions: left=" + var1 + ", top=" + var2 + ", right=" + var3 + ", bottom=" + var4);
      }
   }

   public int getWidth() {
      return this.right - this.left;
   }

   public int getHeight() {
      return this.bottom - this.top;
   }

   public static ThreadModuleDump70 of(int var0, int var1, int var2, int var3) {
      int var4;
      try {
         var4 = Math.addExact(var0, var2);
      } catch (Exception var8) {
         var4 = Integer.MAX_VALUE;
      }

      int var5;
      try {
         var5 = Math.addExact(var1, var3);
      } catch (Exception var7) {
         var5 = Integer.MAX_VALUE;
      }

      return new ThreadModuleDump70(var0, var1, var4, var5);
   }

   public boolean intersects(ThreadModuleDump70 var1) {
      return this.getLeft() < var1.getRight() && this.getRight() > var1.getLeft() && this.getTop() < var1.getBottom() && this.getBottom() > var1.getTop();
   }

   public boolean contains(ThreadModuleDump70 var1) {
      return var1.getLeft() >= this.getLeft()
         && var1.getTop() >= this.getTop()
         && var1.getRight() <= this.getRight()
         && var1.getBottom() <= this.getBottom();
   }

   public ThreadModuleDump70 scale(double var1, double var3) {
      return new ThreadModuleDump70((int)(this.getLeft() * var1), (int)(this.getTop() * var3), (int)(this.getRight() * var1), (int)(this.getBottom() * var3));
   }

   public static ThreadModuleDump70 union(ThreadModuleDump70 var0, ThreadModuleDump70 var1) {
      return new ThreadModuleDump70(
         Math.min(var0.getLeft(), var1.getLeft()),
         Math.min(var0.getTop(), var1.getTop()),
         Math.max(var0.getRight(), var1.getRight()),
         Math.max(var0.getBottom(), var1.getBottom())
      );
   }

   public ThreadModuleDump70 intersection(ThreadModuleDump70 var1) {
      return !this.intersects(var1)
         ? null
         : new ThreadModuleDump70(
            Math.max(this.getLeft(), var1.getLeft()),
            Math.max(this.getTop(), var1.getTop()),
            Math.min(this.getRight(), var1.getRight()),
            Math.min(this.getBottom(), var1.getBottom())
         );
   }

   public boolean contains(int var1, int var2) {
      return var1 >= this.getLeft() && var2 >= this.getTop() && var1 <= this.getRight() && var2 <= this.getBottom();
   }

   public int getLeft() {
      return this.left;
   }

   public int getTop() {
      return this.top;
   }

   public int getRight() {
      return this.right;
   }

   public int getBottom() {
      return this.bottom;
   }
}
