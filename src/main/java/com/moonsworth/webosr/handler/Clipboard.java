package com.moonsworth.webosr.handler;

public interface Clipboard {
   String read();

   void write(String text1);

   void clear();
}
