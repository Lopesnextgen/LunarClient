package com.moonsworth.lunar.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface ThreadRequirement {
   ThreadRequirement.Type value();

   enum Type {
      THREAD_MAIN(Annotation4.Type2.THREAD, "$NAME$ must be called from the main thread");

      Type(String var3) {
      }

      Type(Annotation4.Type2 var3, String var4) {
      }
   }

   enum Type2 {
      THREAD;
   }
}
