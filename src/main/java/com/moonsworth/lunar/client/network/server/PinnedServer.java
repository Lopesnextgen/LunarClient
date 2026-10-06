package com.moonsworth.lunar.client.network.server;

public class PinnedServer {
   private final String field1;
   private final String field2;
   private final Long field3;
   private final boolean field4;
   private final boolean field5;

   public PinnedServer(String text, String text2, Long longValue, boolean flag, boolean flag2) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = longValue;
      this.field4 = flag;
      this.field5 = flag2;
   }

   public boolean method1() {
      return this.method3() == -1L || System.currentTimeMillis() <= this.method3();
   }

   public String name() {
      return this.field1;
   }

   public String method2() {
      return this.field2;
   }

   public Long method3() {
      return this.field3;
   }

   public boolean method4() {
      return this.field4;
   }

   public boolean method5() {
      return this.field5;
   }
}
