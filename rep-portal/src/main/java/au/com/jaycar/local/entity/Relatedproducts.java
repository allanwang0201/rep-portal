/*
 * Created on 3 Oct 2017 ( Time 11:55:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package au.com.jaycar.local.entity;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

/**
 * Persistent class for entity stored in table "relatedproducts"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="relatedproducts", catalog="coreinfo" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="Relatedproducts.countAll", query="SELECT COUNT(x) FROM Relatedproducts x" )
} )
public class Relatedproducts implements Serializable
{
    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="PK", nullable=false)
    private Integer    pk           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="sourcecode", length=10)
    private String     sourcecode   ;

    @Column(name="targetcode", length=10)
    private String     targetcode   ;

    @Column(name="relationtype", length=45)
    private String     relationtype ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Relatedproducts()
    {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setPk( Integer pk )
    {
        this.pk = pk ;
    }
    public Integer getPk()
    {
        return this.pk;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : sourcecode ( VARCHAR ) 
    public void setSourcecode( String sourcecode )
    {
        this.sourcecode = sourcecode;
    }
    public String getSourcecode()
    {
        return this.sourcecode;
    }

    //--- DATABASE MAPPING : targetcode ( VARCHAR ) 
    public void setTargetcode( String targetcode )
    {
        this.targetcode = targetcode;
    }
    public String getTargetcode()
    {
        return this.targetcode;
    }

    //--- DATABASE MAPPING : relationtype ( VARCHAR ) 
    public void setRelationtype( String relationtype )
    {
        this.relationtype = relationtype;
    }
    public String getRelationtype()
    {
        return this.relationtype;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(pk);
        sb.append("]:"); 
        sb.append(sourcecode);
        sb.append("|");
        sb.append(targetcode);
        sb.append("|");
        sb.append(relationtype);
        return sb.toString(); 
    } 

}