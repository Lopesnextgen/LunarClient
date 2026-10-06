package com.moonsworth.lunar.client.render.jit;

import java.time.Duration;

public class JitCacheSettings {
   private final Duration field1;
   private final Duration field2;
   private final Long field3;

   public JitCacheSettings(Duration duration, Duration duration2, Long longValue) {
      this.field1 = duration;
      this.field2 = duration2;
      this.field3 = longValue;
   }

   public static JitCacheSettings method1() {
      return new JitCacheSettings(Duration.ofSeconds(5L), Duration.ofSeconds(30L), 536870912L);
   }

   public Duration method2() {
      return this.field1;
   }

   public Duration method3() {
      return this.field2;
   }

   public Long method4() {
      return this.field3;
   }
}
