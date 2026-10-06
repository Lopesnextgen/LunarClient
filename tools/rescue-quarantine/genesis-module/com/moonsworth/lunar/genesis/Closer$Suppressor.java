package com.moonsworth.lunar.genesis;

import java.io.Closeable;
import com.google.common.eventbus.Subscribe;

@Subscribe
interface Closer$Suppressor {
   void suppress(Closeable closeable1, Throwable exception2, Throwable exception3);
}
