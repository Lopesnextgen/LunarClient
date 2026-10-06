package com.moonsworth.lunar.bridge;

import java.util.Collection;
import java.util.Map;

public interface ShaderDefinesBridge {
   Map<String, String> bridge$values();

   Collection<String> bridge$flags();
}
