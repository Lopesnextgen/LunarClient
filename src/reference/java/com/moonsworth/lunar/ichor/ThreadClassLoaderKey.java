package com.moonsworth.lunar.ichor;

public class ThreadClassLoaderKey {
   private final Thread field1;
   private final URLClassLoader field2;

   public ThreadClassLoaderKey(Thread thread, URLClassLoader type) {
      this.field1 = thread;
      this.field2 = type;
   }

   public Thread method1() {
      return this.field1;
   }

   public URLClassLoader method2() {
      return this.field2;
   }
}
