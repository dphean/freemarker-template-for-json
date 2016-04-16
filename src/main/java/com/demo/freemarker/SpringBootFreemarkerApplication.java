package com.demo.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
public class SpringBootFreemarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFreemarkerApplication.class, args);
	}

	@PostConstruct
	public void configureFreemarker() throws Exception {
	  // Create your Configuration instance, and specify if up to what FreeMarker
	  // version (here 2.3.24) do you want to apply the fixes that are not 100%
	  // backward-compatible. See the Configuration JavaDoc for details.
	  Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

	  // Specify the source where the template files come from. Here I set a
	  // plain directory for it, but non-file-system sources are possible too:
	  cfg.setDirectoryForTemplateLoading(new PathResource("./template").getFile());

	  // Set the preferred charset template files are stored in. UTF-8 is
	  // a good choice in most applications:
	  cfg.setDefaultEncoding("UTF-8");

	  // Sets how errors will appear.
	  // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
	  cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	  // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
	  cfg.setLogTemplateExceptions(false);
	  // END-Config

	  
	  
	  // Create the root hash. We use a Map here, but it could be a JavaBean too.
	  Map<String, Object> root = new HashMap<>();

	  // Put string "user" into the root
	  root.put("user", "Big Joe");

	  // Create the "latestProduct" hash. We use a JavaBean here, but it could be a Map too.
	  Map latest = new HashMap();
	  latest.put("url", "products/greenmouse.json");
	  latest.put("name", "green mouse");
	  // and put it into the root
	  root.put("latestProduct", latest);
	  
	  root.put("indexOf", new IndexOfMethod());
	  
	  /*
	   * Can you <#assign upper = "com.demo.freemarker.UpperDirective"?new()>
	   * in template as an option and ommit the line below
	   * 
	   */
	  root.put("upper", new UpperDirective());
	  
	  root.put("repeat", new RepeatDirective());

	  
	  
	  // Template
	  Template temp = cfg.getTemplate("test.json");
	  
	  Writer out = new OutputStreamWriter(System.out);
	  temp.process(root, out);


	}
}
