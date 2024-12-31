package topic.management.helper;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Component(service = ResponseUtil.class)
public class ResponseUtil {
    public Object response(String message, Response.Status status) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(status.getStatusCode()));
        response.put("message", message);
        return response;
    }

}

