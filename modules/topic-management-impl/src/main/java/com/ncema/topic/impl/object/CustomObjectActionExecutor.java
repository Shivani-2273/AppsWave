package com.ncema.topic.impl.object;

import com.liferay.object.action.executor.ObjectActionExecutor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = ObjectActionExecutor.class)
public class CustomObjectActionExecutor implements ObjectActionExecutor {

    private static final Log _log = LogFactoryUtil.getLog(CustomObjectActionExecutor.class);

    @Override
    public void execute(long l, long l1, UnicodeProperties unicodeProperties,
                        JSONObject jsonObject, long l2) {

        _log.info("JSONObject: " + jsonObject);
    }

    @Override
    public String getKey() {
        return "CustomObjectActionExecutor";
    }
}
