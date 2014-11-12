package org.bodywired.api.controller;

import org.bodywired.api.utils.BodywiredURL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentationController {

	public @ResponseBody String sayHelloTo(@PathVariable(value = "name") String name) {
	    return "Hello "+name+" !";    
	  }
	
	public ModelAndView getDocumentation(Model model) {

		List<Item> routes = new ArrayList<Item>();

		try {
			for (Field f : BodywiredURL.class.getDeclaredFields()) {
				routes.add(new Item(f.getName(), f.get(BodywiredURL.class).toString()));
				System.out.println("Field : " + f.getName() + " -> " + f.get(BodywiredURL.class).toString());
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));
        for (BeanDefinition beanDefinition : scanner.findCandidateComponents("org.bodywired.api.controller")){
            System.out.println(beanDefinition.getBeanClassName());
            System.out.println(beanDefinition.getDescription());
            System.out.println(beanDefinition.getFactoryBeanName());
            System.out.println(beanDefinition.getFactoryMethodName());
            System.out.println(beanDefinition.getParentName());
            System.out.println(beanDefinition.getResourceDescription());
            System.out.println(beanDefinition.getScope());
            MutablePropertyValues properties = beanDefinition.getPropertyValues();
            for (PropertyValue p : properties.getPropertyValues() ) {
            	System.out.println(p.getName() + " -> " + p.getValue().toString());
            }
        }
		
		model.addAttribute("routes", routes);
		
		return new ModelAndView("documentation", model.asMap());
	}
	
	public class Item {
		private String name;
		private String url;

		public Item(String name, String url) {
			super();
			this.name = name;
			this.url = url;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
}
