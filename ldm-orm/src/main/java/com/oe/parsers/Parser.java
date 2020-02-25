package com.oe.parsers;

import org.dom4j.Element;

public interface Parser<T> {

    T parse(Element e);
}
