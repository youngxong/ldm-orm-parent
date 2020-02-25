package com.oe.parsers;

import com.oe.mappers.MapperConfig;
import com.oe.mappers.MapperPath;
import com.oe.mappers.MappersConfigCollection;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MappersParser implements Parser<List<MapperPath>> {

    List<MapperPath> mapperPaths;

    public MappersParser() {
        mapperPaths=new ArrayList<MapperPath>();
    }

    public List<MapperPath> parse(Element mappersTag) {
        List<Element> mapperTags = mappersTag.elements("mapper");
        for (Element mapperTag : mapperTags) {
            Attribute attribute = mapperTag.attribute("resource");
            mapperPaths.add(new MapperPath(attribute.getValue()));
        }
        return mapperPaths;
    }
}
