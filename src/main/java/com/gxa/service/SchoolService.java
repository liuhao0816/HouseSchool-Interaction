package com.gxa.service;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 9:46.
 */

import com.gxa.entity.School;

import javax.smartcardio.Card;
import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
public interface SchoolService {
    List<School> selectAllByName(String schoolName);
}
