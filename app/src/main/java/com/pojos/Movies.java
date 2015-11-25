
package com.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movies {

    private Integer page;
    private List<MovieObj> movieObjs = new ArrayList<MovieObj>();
    private Integer totalPages;
    private Integer totalResults;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 
     * @return
     *     The movieObjs
     */
    public List<MovieObj> getMovieObjs() {
        return movieObjs;
    }

    /**
     * 
     * @param movieObjs
     *     The movieObjs
     */
    public void setMovieObjs(List<MovieObj> movieObjs) {
        this.movieObjs = movieObjs;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
