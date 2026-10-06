package com.moonsworth.webosr.handler;

import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.PromiseJS;

@FunctionalInterface
public interface FunctionJS {
   void invoke(PromiseJS<?> promisejs1, Browser browser2, String text3, String[] items4);
}
