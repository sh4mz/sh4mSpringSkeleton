package net.sh4m.genericjdbc.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import net.sh4m.genericjdbc.util.JsonDateSerializer;


public abstract class AbstractDTO<ID extends Serializable> implements
        Persistable<ID>
{

    private ID id;
    private Boolean deleted = Boolean.FALSE;
    private Date createdDate;
    private Date lastModifiedDate;
    //private Integer version;

    /**
     * {@inheritDoc}
     */
    @Override
    @XmlTransient
    public ID getId()
    {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(ID id)
    {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    @XmlTransient
    public boolean isNew()
    {
        return id == null;
    }

    /**
     * @return returns the created date
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreatedDate()
    {
        return createdDate != null ? new Date(createdDate.getTime()) : null;
    }

    /**
     * @param createdDate
     *            the created date to set
     */
    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = (createdDate != null) ? new Date(
                createdDate.getTime()) : null;
    }

    /**
     * @return returns the modified date
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getLastModifiedDate()
    {
        return lastModifiedDate != null ? new Date(lastModifiedDate.getTime())
                : null;
    }

    /**
     * @param lastModifiedDate
     *            the modified date to set
     */
    public void setLastModifiedDate(Date lastModifiedDate)
    {
        this.lastModifiedDate = (lastModifiedDate != null) ? new Date(
                lastModifiedDate.getTime()) : null;
    }

//    /**
//     * @return the version
//     */
//    @JsonIgnore
//    @XmlTransient
//    public Integer getVersion()
//    {
//        return version;
//    }
//
//    /**
//     * @param version
//     *            the version to set
//     */
//    public void setVersion(Integer version)
//    {
//        this.version = version;
//    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted()
    {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

    /**
     * @return the created date in millisecond
     */
    @JsonIgnore
    @XmlTransient
    public long getCreatedDateMs()
    {
        return (createdDate != null) ? new Date(createdDate.getTime())
                .getTime() : 0;
    }

    /**
     * @return the last modified date in millisecond
     */
    @JsonIgnore
    @XmlTransient
    public long getLastModifiedDateMs()
    {
        return (lastModifiedDate != null) ? new Date(lastModifiedDate.getTime())
                .getTime() : 0;
    }

}
