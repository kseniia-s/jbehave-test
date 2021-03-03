package project.pageObjects.components.enums;

public enum FilterTypeEnum {
    MANUFACTURER("Manufacturer"),
    PRICE_MIN("Price min"),
    PRICE_MAX("Price max"),
    CPU("CPU"),
    RAM("RAM size"),
    OS("OS");

    FilterTypeEnum(String filterName) {
        this.filterName = filterName;
    }

    private String filterName;

    public String getFilterName() {
        return filterName;
    }

    public static FilterTypeEnum getByName(String name) {
        for (FilterTypeEnum ft: FilterTypeEnum.values()) {
            if (name.trim().equals(ft.getFilterName())) {
                return ft;
            }
        }
        throw new RuntimeException("No filter found");
    }
}