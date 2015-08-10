package com.oocl.jyhon.service;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * Created by WhiteSaber on 15/8/8.
 */
public interface AuditService {
    public Map<String, Object> getRequest(ServletContext servletContext);

}
