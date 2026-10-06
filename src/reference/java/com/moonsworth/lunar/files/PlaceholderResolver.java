package com.moonsworth.lunar.files;

import java.util.Collection;

public interface PlaceholderResolver {
   Collection<VersionPlaceholder> method1();

   default String method2(String text) {
      if (text != null && !text.isEmpty()) {
         for (VersionPlaceholder files53 : this.method1()) {
            String text4 = files53.value();
            if (text4 == null) {
               text4 = "null";
            }

            text = text.replace("${" + files53.key() + "}", text4);
         }

         return text;
      } else {
         return text;
      }
   }
}
