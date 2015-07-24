package com.didi.biz.addr.search;

import java.io.Serializable;

/**
 * 搜索配置
 *
 * @author houshengyong
 * @since 2015-5-29
 */

public class SearchConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    // 搜索地址类型，出发地，目的地，机场
    public enum AddressType {
        FromPoint, ToPoint, Airport
    }

    public AddressType mAddressType = AddressType.ToPoint;
}
