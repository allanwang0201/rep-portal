package au.com.jaycar.mdm.entity;

public class MediasWithBLOBs extends Medias {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medias.url
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medias.p_location
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    private String pLocation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medias.p_foreigndataowners
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    private String pForeigndataowners;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medias.url
     *
     * @return the value of medias.url
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medias.url
     *
     * @param url the value for medias.url
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medias.p_location
     *
     * @return the value of medias.p_location
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public String getpLocation() {
        return pLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medias.p_location
     *
     * @param pLocation the value for medias.p_location
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medias.p_foreigndataowners
     *
     * @return the value of medias.p_foreigndataowners
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public String getpForeigndataowners() {
        return pForeigndataowners;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medias.p_foreigndataowners
     *
     * @param pForeigndataowners the value for medias.p_foreigndataowners
     *
     * @mbg.generated Thu Oct 26 10:16:21 AEDT 2017
     */
    public void setpForeigndataowners(String pForeigndataowners) {
        this.pForeigndataowners = pForeigndataowners;
    }
}