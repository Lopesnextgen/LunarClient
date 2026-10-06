package com.moonsworth.lunar.genesis;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.base.Strings;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
final class PrimitivesPlatform {
   private static final Logger field1 = Logger.getLogger(PrimitivesPlatform.class.getName());

   static void checkGwtRpcEnabled() {
      String text0 = "guava.gwt.emergency_reenable_rpc";
      if (!Boolean.parseBoolean(System.getProperty(text0, "false"))) {
         throw new UnsupportedOperationException(
            Strings.lenientFormat(
               "We are removing GWT-RPC support for Guava types. You can temporarily reenable support by setting the system property %s to true. For more about system properties, see %s. For more about Guava's GWT-RPC support, see %s.",
               text0,
               "https://stackoverflow.com/q/5189914/28465",
               "https://groups.google.com/d/msg/guava-announce/zHZTFg7YF3o/rQNnwdHeEwAJ"
            )
         );
      }

      field1.log(
         Level.WARNING,
         "Later in 2020, we will remove GWT-RPC support for Guava types. You are seeing this warning because you are sending a Guava type over GWT-RPC, which will break. You can identify which type by looking at the class name in the attached stack trace.",
         new Throwable()
      );
   }

   private PrimitivesPlatform() {
   }
}
