package com.moonsworth.lunar.bridge;

/**
 * Source twin of the jar-only {@code Bridge3_4}; extending it keeps the
 * renamed interface assignable to the stale-jar signatures that still
 * reference the original name.
 */
public interface TextureBridge extends Bridge3_4 {
   void method1(Bridge11_2 bridge11_21, Bridge8Extension34 bridge8extension342);

   void method22();
}
