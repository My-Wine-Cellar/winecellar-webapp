/*
 * My-Wine-Cellar, copyright 2019
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.nav;

import info.mywinecellar.model.BarrelComponent;
import info.mywinecellar.model.GrapeComponent;
import info.mywinecellar.model.Wine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Session scope functionality
 */
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

    /**
     * Get the current country identifier
     * @return The identifier, or <code>null</code>
     */
    public static Long getCountryId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(COUNTRY_ID, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current region identifier
     * @return The identifier, or <code>null</code>
     */
    public static Long getRegionId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(REGION_ID, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current area identifier
     * @return The identifier, or <code>null</code>
     */
    public static Long getAreaId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(AREA_ID, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current producer identifier
     * @return The identifier, or <code>null</code>
     */
    public static Long getProducerId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(PRODUCER_ID, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current wine identifier
     * @return The identifier, or <code>null</code>
     */
    public static Long getWineId() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Long) ra.getAttribute(WINE_ID, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Update the attributes
     * @param countryId The country
     * @param regionId The region
     * @param areaId The area
     * @param producerId The producer
     * @param wineId The wine
     */
    public static void updateSessionAttributes(Long countryId, Long regionId, Long areaId,
                                               Long producerId, Long wineId) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        ra.setAttribute(Session.COUNTRY_ID, countryId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.REGION_ID, regionId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.AREA_ID, areaId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.PRODUCER_ID, producerId, RequestAttributes.SCOPE_SESSION);
        ra.setAttribute(Session.WINE_ID, wineId, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current wine
     * @return The wine, or <code>null</code>
     */
    public static Wine getWine() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (Wine) ra.getAttribute(Session.WINE, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get the current grape component
     * @return The grape component, or <code>null</code>
     */
    public static GrapeComponent getGrapeComponent() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (GrapeComponent) ra.getAttribute(Session.GRAPE_COMPONENT, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Set the current grape component
     * @param gc The grape component
     */
    public static void setGrapeComponent(GrapeComponent gc) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        List<GrapeComponent> grapes = (List<GrapeComponent>) ra.getAttribute(Session.GRAPE_COMPONENTS,
                RequestAttributes.SCOPE_SESSION);

        if (grapes == null) {
            grapes = new ArrayList<>();
        }

        grapes.add(gc);
        ra.setAttribute(Session.GRAPE_COMPONENTS, grapes, RequestAttributes.SCOPE_SESSION);

    }

    /**
     * Get all grape components
     * @return The grape components, or <code>null</code>
     */
    public static List<GrapeComponent> getGrapeComponents() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (List<GrapeComponent>) ra.getAttribute(Session.GRAPE_COMPONENTS, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Set barrel component
     * @param bc The barrel component
     */
    public static void setBarrelComponent(BarrelComponent bc) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();

        List<BarrelComponent> barrels = (List<BarrelComponent>) ra.getAttribute(Session.BARREL_COMPONENTS,
                RequestAttributes.SCOPE_SESSION);

        if (barrels == null) {
            barrels = new ArrayList<>();
        }

        barrels.add(bc);
        ra.setAttribute(Session.BARREL_COMPONENTS, barrels, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Get all barrel components
     * @return The barrel components, or <code>null</code>
     */
    public static List<BarrelComponent> getBarrelComponents() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return (List<BarrelComponent>) ra.getAttribute(Session.BARREL_COMPONENTS, RequestAttributes.SCOPE_SESSION);
    }

    /**
     * Clear
     * @param status The session status
     */
    public static void clear(SessionStatus status) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        ra.removeAttribute(Session.WINE, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.GRAPE_COMPONENT, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.GRAPE_COMPONENTS, RequestAttributes.SCOPE_SESSION);
        ra.removeAttribute(Session.BARREL_COMPONENTS, RequestAttributes.SCOPE_SESSION);
        status.setComplete();
    }

}
