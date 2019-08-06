package com.cellar.wine.nav;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class Session {

    public static final String COUNTRY_ID = "countryId";

    public static final String REGION_ID = "regionId";

    public static final String AREA_ID = "areaId";

    public static final String PRODUCER_ID = "producerId";

    public static final String WINE_ID = "wineId";

    public static Long getCountryId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long)ra.getAttribute(COUNTRY_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getRegionId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long)ra.getAttribute(REGION_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getAreaId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long)ra.getAttribute(AREA_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getProducerId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long)ra.getAttribute(PRODUCER_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getWineId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long)ra.getAttribute(WINE_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static void updateSessionAttributes(Long countryId, Long regionId, Long areaId,
                                               Long producerId, Long wineId) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        ra.setAttribute(Session.COUNTRY_ID, countryId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.REGION_ID, regionId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.AREA_ID, areaId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.PRODUCER_ID, producerId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.WINE_ID, wineId, RequestAttributes.SCOPE_SESSION);
    }
}
