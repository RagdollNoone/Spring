package com.component;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class StringToAdminUserConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getType().equals(String.class) && targetType.getType().equals(AdminUser.class);
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, AdminUser.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        AdminUser adminUser = new AdminUser();
        adminUser.setName((String) source);

        return adminUser;
    }

    public static void main(String[] args) {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToAdminUserConverter());
        AdminUser adminUser = conversionService.convert("michael", AdminUser.class);
        System.out.println(adminUser);
    }
}
