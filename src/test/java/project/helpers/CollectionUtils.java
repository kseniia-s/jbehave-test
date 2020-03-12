package project.helpers;

import freemarker.template.utility.NullArgumentException;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static <T extends BaseItem> List<T> getItemsList(List<WebElement> list, Class<T> clazz) {

        if (!list.isEmpty()) {
            return list.stream()
                    .map(item -> createInstance(clazz, item))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        throw new NullArgumentException("List of elements is empty!");
    }

    private static <T extends BaseItem> T createInstance(Class<T> clazz, WebElement item) {
        try {
            return clazz.getDeclaredConstructor(WebElement.class).newInstance(item);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
