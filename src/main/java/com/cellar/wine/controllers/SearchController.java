package com.cellar.wine.controllers;

import com.cellar.wine.nav.Paths;
import com.cellar.wine.ui.SearchUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Resource
    private DataSource ds;

    public SearchController() {
    }

    private boolean isValidToken(String token) {
        if (token == null || "".equals(token))
            return false;

        if ("AND".equalsIgnoreCase(token) || "OR".equalsIgnoreCase(token) || "NOT".equalsIgnoreCase(token))
            return false;

        if (token.startsWith("\'"))
            return false;

        return true;
    }

    private List<SearchUI> search(String input, String table, SearchUI.SearchResult type) throws Exception {
        List<SearchUI> l = new ArrayList<>();
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
            sb = sb.append("SELECT id, name, description FROM ");
            sb = sb.append(table);
            sb = sb.append(" WHERE to_tsvector(\'english\', coalesce(name, \'\') || \' \' || coalesce(description, \'\')) @@");
            sb = sb.append(" to_tsquery(\'");
            sb = sb.append(validInput);
            sb = sb.append("\');");

            boolean result = stmt.execute(sb.toString());
            if (result) {
                rs = stmt.getResultSet();

                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);

                    l.add(new SearchUI(type, id, name, description));
                }
            }

            System.out.println(l);
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

    private List<SearchUI> searchArea(String input) throws Exception {
        return search(input, "area", SearchUI.SearchResult.AREA);
    }

    private List<SearchUI> searchCountry(String input) throws Exception {
        return search(input, "country", SearchUI.SearchResult.COUNTRY);
    }

    private List<SearchUI> searchProducer(String input) throws Exception {
        return search(input, "producer", SearchUI.SearchResult.PRODUCER);
    }

    private List<SearchUI> searchRegion(String input) throws Exception {
        return search(input, "region", SearchUI.SearchResult.REGION);
    }

    private List<SearchUI> searchWine(String input) throws Exception {
        return search(input, "wine", SearchUI.SearchResult.WINE);
    }

    @GetMapping("/new")
    public String searchNewGet() {
        String input = "Poggio";

        try {
            searchProducer(input);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return Paths.LANDING_PAGE;
    }
}
