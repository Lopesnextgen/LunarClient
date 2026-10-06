package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Map;
import com.google.common.base.Function;

final class Tables$1 implements Function<Map<Object, Object>, Map<Object, Object>> {
   Tables$1() {
   }

   public Map<Object, Object> apply(Map<Object, Object> map1) {
      return Collections.unmodifiableMap(map1);
   }
}
