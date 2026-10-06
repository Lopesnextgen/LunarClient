package com.moonsworth.lunar.ichor;

import java.io.InputStream;
import java.net.URL;

public interface ResourceLookup {
   InputStream getResourceAsStream(String text1);

   URL findResource(String text1);
}
