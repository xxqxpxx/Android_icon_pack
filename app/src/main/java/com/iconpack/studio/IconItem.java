package com.iconpack.studio;

public class IconItem {
    
    private String iconName;
    private int iconResource;
    private String packageName;
    
    public IconItem(String iconName, int iconResource, String packageName) {
        this.iconName = iconName;
        this.iconResource = iconResource;
        this.packageName = packageName;
    }
    
    public String getIconName() {
        return iconName;
    }
    
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
    
    public int getIconResource() {
        return iconResource;
    }
    
    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}

