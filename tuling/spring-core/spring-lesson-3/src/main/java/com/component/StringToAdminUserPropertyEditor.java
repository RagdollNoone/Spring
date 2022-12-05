package com.component;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class StringToAdminUserPropertyEditor extends PropertyEditorSupport
implements PropertyEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        AdminUser adminUser = new AdminUser();
        adminUser.setName(text);

        this.setValue(adminUser);
    }

    public static void main(String[] args) {
        StringToAdminUserPropertyEditor propertyEditor = new StringToAdminUserPropertyEditor();
        propertyEditor.setAsText("michael");

        AdminUser adminUser = (AdminUser) propertyEditor.getValue();
        System.out.println(adminUser);
    }
}
