package com.gxa.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**范围
 */
@Data
public class Scope implements Serializable {

    private Integer id;

    private Content content;

    private String notifyScope;
}
