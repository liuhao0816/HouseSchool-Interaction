package com.gxa.mapper;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/11
 * atime 16:47.
 */

import com.gxa.entity.School;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;
import java.util.List;
import java.util.Map;

/**
 *@author renmuqiao
 *@date 2022-11-11
 *
 */
@Repository
public interface SchoolMapper {

    List<School> selectAllByName(Map map);

}
