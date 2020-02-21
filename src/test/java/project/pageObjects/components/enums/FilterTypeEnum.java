package project.pageObjects.components.enums;

public enum FilterTypeEnum {
    MANUFACTURER("Производитель"),
    MANUFACTURER_SEARCH("Производитель"),
    PRICE_MIN("Цена min"),
    PRICE_MAX("Цена max"),
    CPU("Процессор"),
    RAM("Объем оперативной памяти"),
    OS("Операционная система"),
    RAM_TYPE("Тип оперативной памяти");

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