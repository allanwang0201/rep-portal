package au.com.jaycar.warehouse.mapping;

import au.com.jaycar.warehouse.entity.Porder;
import au.com.jaycar.warehouse.entity.PorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PorderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    long countByExample(PorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    int deleteByExample(PorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    int insert(Porder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    int insertSelective(Porder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    List<Porder> selectByExample(PorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    int updateByExampleSelective(@Param("record") Porder record, @Param("example") PorderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MDST.PORDER
     *
     * @mbg.generated Wed Oct 11 11:53:15 AEDT 2017
     */
    int updateByExample(@Param("record") Porder record, @Param("example") PorderExample example);
}