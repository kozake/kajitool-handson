package kajitool.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SequenceMapper {

    @Select("SELECT ${sequenceName}.NEXTVAL")
    long nextval(@Param("sequenceName") String sequenceName);
}
