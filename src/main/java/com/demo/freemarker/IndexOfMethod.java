package com.demo.freemarker;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class IndexOfMethod implements TemplateMethodModelEx {

  @Override
  public TemplateModel exec(List args) throws TemplateModelException {
    if (args.size() != 2) {
        throw new TemplateModelException("Wrong arguments");
    }
    
    SimpleScalar one = (SimpleScalar) args.get(1);
    SimpleScalar two = (SimpleScalar) args.get(0);
    
    int indexOf = one.getAsString().indexOf(two.getAsString());
    
    return new SimpleNumber(indexOf);
        //((String) args.get(1)).indexOf((String) args.get(0)));
}
  
}