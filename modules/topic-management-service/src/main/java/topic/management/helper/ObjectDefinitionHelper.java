package topic.management.helper;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        service = ObjectDefinitionHelper.class
)
public class ObjectDefinitionHelper {

    @Reference
    private ObjectDefinitionLocalService _objectDefinitionLocalService;

    public ObjectDefinition getObjectDefinition(long companyId, String objectDefinitionKey) {
        return _objectDefinitionLocalService.fetchObjectDefinition(
                companyId, objectDefinitionKey);
    }
}
