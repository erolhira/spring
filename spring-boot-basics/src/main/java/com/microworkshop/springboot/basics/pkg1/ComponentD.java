package com.microworkshop.springboot.basics.pkg1;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ComponentD {

}
