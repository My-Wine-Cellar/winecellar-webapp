/*
 * My-Wine-Cellar, copyright 2022
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.controller;

import info.mywinecellar.dto.SearchDto;
import info.mywinecellar.nav.Paths;
import info.mywinecellar.util.SearchSorter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import jakarta.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Search
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Resource
    private DataSource ds;

    /**
     * Constructor
     */
    public SearchController() {
    }

    /**
     * Is the token valid - keywords and guard against SQL injection
     *
     * @param token The token
     * @return True if valid, other false
     */
    private boolean isValidToken(String token) {
        if (token == null || "".equals(token)) {
            return false;
        }
        if ("AND".equalsIgnoreCase(token) || "OR".equalsIgnoreCase(token) || "NOT".equalsIgnoreCase(token)) {
            return false;
        }
        if (token.startsWith("\'")) {
            return false;
        }
        return true;
    }

    /**
     * Search a table
     *
     * @param input The search input
     * @param table The table name
     * @param type  The search type
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> search(String input, String table, SearchDto.SearchResult type) throws Exception {
        List<SearchDto> l = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            c = ds.getConnection();
            stmt = c.createStatement();

            StringBuilder validInput = new StringBuilder();
            StringTokenizer st = new StringTokenizer(input, ", ");

            boolean separator = true;
            while (st.hasMoreTokens()) {
                String token = st.nextToken().trim();

                if (isValidToken(token)) {
                    if (validInput.length() > 0 && separator) {
                        validInput = validInput.append(" & ");
                    }

                    validInput = validInput.append(token);
                    separator = true;
                } else {
                    if (st.hasMoreTokens()) {
                        if ("AND".equalsIgnoreCase(token)) {
                            separator = true;
                        } else if ("OR".equalsIgnoreCase(token)) {
                            validInput = validInput.append(" | ");
                            separator = false;
                        } else if ("NOT".equalsIgnoreCase(token)) {
                            validInput = validInput.append(" & ! ");
                            separator = false;
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb = sb.append("SELECT id, name, description, ts_rank_cd(vector, query) AS rank FROM ");
            sb = sb.append(table);
            sb = sb.append(", to_tsvector(\'english\', coalesce(name, \'\') || \' \' || coalesce(description, \'\'))");
            sb = sb.append(" vector, ");
            sb = sb.append("to_tsquery(\'");
            sb = sb.append(validInput);
            sb = sb.append("\') query ");
            sb = sb.append("WHERE vector @@ query;");

            boolean result = stmt.execute(sb.toString());
            if (result) {
                rs = stmt.getResultSet();

                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    Double rank = rs.getDouble(4);

                    l.add(new SearchDto(type, id, name, description, rank));
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {
                    // Nothing
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignore) {
                    // Nothing
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ignore) {
                    // Nothing
                }
            }
        }

        return l;
    }

    /**
     * Search area
     *
     * @param input The search input
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> searchArea(String input) throws Exception {
        return search(input, "area", SearchDto.SearchResult.AREA);
    }

    /**
     * Search country
     *
     * @param input The search input
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> searchCountry(String input) throws Exception {
        return search(input, "country", SearchDto.SearchResult.COUNTRY);
    }

    /**
     * Search producer
     *
     * @param input The search input
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> searchProducer(String input) throws Exception {
        return search(input, "producer", SearchDto.SearchResult.PRODUCER);
    }

    /**
     * Search region
     *
     * @param input The search input
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> searchRegion(String input) throws Exception {
        return search(input, "region", SearchDto.SearchResult.REGION);
    }

    /**
     * Search wine
     *
     * @param input The search input
     * @return The list of results
     * @throws Exception In case of an error
     */
    private List<SearchDto> searchWine(String input) throws Exception {
        return search(input, "wine", SearchDto.SearchResult.WINE);
    }

    /**
     * Search
     *
     * @return The page identifier
     */
    @GetMapping("/new")
    public String searchNewGet() {
        List<SearchDto> l = new ArrayList<>();
        String input = "Poggio";

        try {
            l.addAll(searchProducer(input));

            Collections.sort(l, new SearchSorter());
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return Paths.LANDING_PAGE;
    }
}
