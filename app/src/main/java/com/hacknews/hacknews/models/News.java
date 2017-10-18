package com.hacknews.hacknews.models;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

/**
 * Created by Eshan on 10/18/17.
 */

@Entity
public class News
{


    @Id
    private long id;
    @Backlink
    public ToMany<Hit> hits;
    private Integer nbHits;
    private Integer page;
    private Integer nbPages;
    private Integer hitsPerPage;
    private Integer processingTimeMS;
    private Boolean exhaustiveNbHits;
    private String query;
    private String params;


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public ToMany<Hit> getHits()
    {
        return hits;
    }

    public void setHits(ToMany<Hit> hits)
    {
        this.hits = hits;
    }

    public Integer getNbHits()
    {
        return nbHits;
    }

    public void setNbHits(Integer nbHits)
    {
        this.nbHits = nbHits;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getNbPages()
    {
        return nbPages;
    }

    public void setNbPages(Integer nbPages)
    {
        this.nbPages = nbPages;
    }

    public Integer getHitsPerPage()
    {
        return hitsPerPage;
    }

    public void setHitsPerPage(Integer hitsPerPage)
    {
        this.hitsPerPage = hitsPerPage;
    }

    public Integer getProcessingTimeMS()
    {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(Integer processingTimeMS)
    {
        this.processingTimeMS = processingTimeMS;
    }

    public Boolean getExhaustiveNbHits()
    {
        return exhaustiveNbHits;
    }

    public void setExhaustiveNbHits(Boolean exhaustiveNbHits)
    {
        this.exhaustiveNbHits = exhaustiveNbHits;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

}