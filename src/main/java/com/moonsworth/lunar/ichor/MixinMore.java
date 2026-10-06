package com.moonsworth.lunar.ichor;

import java.io.InputStream;
import java.net.URL;

public interface MixinMore {
   InputStream getResourceAsStream(String var1);

   URL findResource(String var1);
}
