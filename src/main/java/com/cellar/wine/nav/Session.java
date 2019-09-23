package com.cellar.wine.nav;

import com.cellar.wine.models.BarrelComponent;
import com.cellar.wine.models.GrapeComponent;
import com.cellar.wine.models.Wine;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private Session() {
    }

    private static final String COUNTRY_ID = "countryId";
    private static final String REGION_ID = "regionId";
    private static final String AREA_ID = "areaId";
    private static final String PRODUCER_ID = "producerId";
    private static final String WINE_ID = "wineId";

    private static final String WINE = "wine";
    private static final String GRAPE_COMPONENT = "grapeComponent";
    private static final String GRAPE_COMPONENTS = "grapeComponents";
    private static final String BARREL_COMPONENTS = "barrelComponents";

    public static Long getCountryId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(COUNTRY_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getRegionId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(REGION_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getAreaId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(AREA_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getProducerId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(PRODUCER_ID, RequestAttributes.SCOPE_SESSION);
    }

    public static Long getWineId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(WINE_ID, RequestAttributes.SCOPE_SESSION);
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

    public static Wine getWine() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Wine) ra.getAttribute(Session.WINE, RequestAttributes.SCOPE_SESSION);
    }

    public static GrapeComponent getGrapeComponent() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (GrapeComponent) ra.getAttribute(Session.GRAPE_COMPONENT, RequestAttributes.SCOPE_SESSION);
    }

    public static void setGrapeComponent(GrapeComponent gc) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        List<GrapeComponent> grapes = (List<GrapeComponent>) ra.getAttribute(Session.GRAPE_COMPONENTS,
                RequestAttributes.SCOPE_SESSION);

        if (grapes == null)
            grapes = new ArrayList<>();

        grapes.add(gc);
        ra.setAttribute(Session.GRAPE_COMPONENTS, grapes, RequestAttributes.SCOPE_SESSION);

    }

    public static List<GrapeComponent> getGrapeComponents() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (List<GrapeComponent>) ra.getAttribute(Session.GRAPE_COMPONENTS, RequestAttributes.SCOPE_SESSION);
    }

    public static void setBarrelComponent(BarrelComponent bc) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        List<BarrelComponent> barrels = (List<BarrelComponent>) ra.getAttribute(Session.BARREL_COMPONENTS,
                RequestAttributes.SCOPE_SESSION);

        if (barrels == null)
            barrels = new ArrayList<>();

        barrels.add(bc);
        ra.setAttribute(Session.BARREL_COMPONENTS, barrels, RequestAttributes.SCOPE_SESSION);
    }

    public static List<BarrelComponent> getBarrelComponents() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (List<BarrelComponent>) ra.getAttribute(Session.BARREL_COMPONENTS, RequestAttributes.SCOPE_SESSION);
    }

    public static void clear(SessionStatus status) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        ra.removeAttribute(Session.WINE, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.GRAPE_COMPONENT, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.GRAPE_COMPONENTS, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.BARREL_COMPONENTS, RequestAttributes.SCOPE_SESSION);
        status.setComplete();
    }

}